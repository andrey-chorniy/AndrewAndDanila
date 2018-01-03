package com.company;

import java.io.File;
import java.io.FileFilter;

public class ExtensionFilter implements FileFilter {
    private FileType fileType;

    public ExtensionFilter(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public boolean accept(File file) {
        return file.isDirectory() || FileUtils.isAppropriate(file, fileType);
    }
}