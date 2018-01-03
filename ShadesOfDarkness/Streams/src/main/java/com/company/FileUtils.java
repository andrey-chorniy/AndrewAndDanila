package com.company;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {
    public FileUtils() {
    }

    public static boolean isAppropriate(File file, FileType fileType) {
        return getFileType(file) == fileType && fileType != null;
    }

    public static FileType getFileType(File file) {
        String extension = getExtension(file);
        for (FileType fileType : FileType.values()) {
            if (fileType.isAppropriateExtension(extension)) {
                return fileType;
            }
        }
        return null;
    }

    private static String getExtension(File file) {
        String filename = file.getName();
        int i = filename.lastIndexOf('.');
        return (i > 0 && i < filename.length() - 1) ? filename.substring(i + 1).toLowerCase() : "";
    }

    public List<File> getFileList(File file, FileType fileType) {
        File[] subFiles = file.listFiles(new ExtensionFilter(fileType));
        if (subFiles != null && subFiles.length != 0) {
            return Arrays.asList(subFiles);
        }
        return new ArrayList<>();
    }

    public String getArchiveName(FileType fileType) {
        return fileType.name().toLowerCase() + "s.zip";
    }

    public void processFile(File file, String path, ZipOutputStream output) throws IOException {
        try (FileInputStream input = new FileInputStream(file)) {
            output.putNextEntry(new ZipEntry(path));
            output.write(IOUtils.toByteArray(input));
        }
    }
}