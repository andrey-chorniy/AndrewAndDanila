package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DFSTask implements Runnable {
    private File source;
    private File destination;
    private FileType fileType;
    private FileUtils fileUtils;

    public DFSTask(File source, File containingFolder, FileType fileType) {
        this.fileUtils = new FileUtils();
        this.source = source;
        this.destination = new File(containingFolder.getPath() +
                "/" + fileUtils.getArchiveName(fileType));
        this.fileType = fileType;
    }

    @Override
    public void run() {
        try (ZipOutputStream output = new ZipOutputStream(new FileOutputStream(destination))) {
            recursiveCall(source, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recursiveCall(File file, ZipOutputStream output) throws IOException {
        List<File> fileList = fileUtils.getFileList(file, fileType);
        for (File element : fileList) {
            processElement(element, element.getPath(), output);
        }
        for (File element : fileList) {
            if (element.isDirectory()) {
                recursiveCall(element, output);
            }
        }
    }

    private void processElement(File element, String path, ZipOutputStream output)
            throws IOException {
        if (element.isDirectory()) {
            processDirectory(path, output);
        } else {
            fileUtils.processFile(element, path, output);
        }
    }

    private void processDirectory(String path, ZipOutputStream output)
            throws IOException {
        output.putNextEntry(new ZipEntry(path.endsWith("/") ? path : path + "/"));
    }
}