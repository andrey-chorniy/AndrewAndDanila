package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        File source = new File("C:/f1/");
        File destination = new File("E:/zips/");
        System.out.println("Select search, please(1 - BFS, 2 - DFS).");
        String selectedSearch = bufferedReader.readLine();
        Search search = new Search();
        if (selectedSearch.equals("1")) {
            search.bfs(source, destination);
        } else if (selectedSearch.equals("2")) {
            search.dfs(source, destination);
        }
        search.stop();
    }
}