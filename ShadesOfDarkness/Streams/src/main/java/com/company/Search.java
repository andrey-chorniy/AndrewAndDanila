package com.company;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Search {
    private ExecutorService executorService;
    private static final int DEFAULT_POOL_SIZE = 10;

    public Search() {
        executorService = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);
    }

    public void bfs(File source, File destination) {
        for (FileType fileType : FileType.values()) {
            executorService.submit(new BFSTask(source, destination, fileType));
        }
    }

    public void dfs(File source, File destination) {
        for (FileType fileType : FileType.values()) {
            executorService.submit(new DFSTask(source, destination, fileType));
        }
    }

    public void stop() {
        executorService.shutdown();
    }
}