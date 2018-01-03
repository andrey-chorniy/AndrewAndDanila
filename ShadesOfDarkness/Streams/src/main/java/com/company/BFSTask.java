package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BFSTask implements Runnable {
    private File source;
    private File destination;
    private FileUtils fileUtils;
    private FileType fileType;
    private Queue<File> servantQueue;

    public BFSTask(File source, File containingFolder, FileType fileType) {
        this.fileUtils = new FileUtils();
        this.source = source;
        this.destination = new File(containingFolder.getPath() +
                "/" + fileUtils.getArchiveName(fileType));
        this.fileType = fileType;
        this.servantQueue = new ArrayDeque<>();
    }

    @Override
    public void run() {
        try (ZipOutputStream output = new ZipOutputStream(new FileOutputStream(destination))) {
            servantQueue.add(source);
            while (!servantQueue.isEmpty()) {
                File file = servantQueue.remove();
                for (File element : fileUtils.getFileList(file, fileType)) {
                    processElement(element, element.getPath(), output);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processElement(File element, String path, ZipOutputStream output)
            throws IOException {
        if (element.isDirectory()) {
            processDirectory(element, path, output);
        } else {
            fileUtils.processFile(element, path, output);
        }
    }

    private void processDirectory(File directory, String path, ZipOutputStream output)
            throws IOException {
        servantQueue.add(directory);
        output.putNextEntry(new ZipEntry(path.endsWith("/") ? path : path + "/"));
    }
}