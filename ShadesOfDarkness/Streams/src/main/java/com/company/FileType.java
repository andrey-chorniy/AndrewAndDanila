package com.company;

import java.util.Arrays;
import java.util.HashSet;

public enum FileType {
    AUDIO(new HashSet<>(Arrays.asList("mp3", "wav", "wma"))),
    VIDEO(new HashSet<>(Arrays.asList("avi", "mp4", "flv"))),
    IMAGE(new HashSet<>(Arrays.asList("jpeg", "jpg", "gif", "png")));

    private HashSet<String> extensions;

    FileType(HashSet<String> set) {
        extensions = set;
    }

    boolean isAppropriateExtension(String extension) {
        return extensions.contains(extension);
    }
}