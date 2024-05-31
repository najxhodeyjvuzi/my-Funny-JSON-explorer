package org.example;

import com.google.gson.Gson;

import java.util.*;
import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        printHelloWorld();
    }

    public static void printHelloWorld() {
        System.out.println("Hello World!");
        jsonReader();
    }

    public static void jsonReader() {
        Gson gson = new Gson();
        String filepath = "input/input.json";
        String json = "";
        try {
            json = new String(Files.readAllBytes(new File(filepath
            ).toPath()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map map = gson.fromJson(json, Map.class);
        System.out.println(map);
//      考虑嵌套，逐级输出
        for (Object key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
            if (map.get(key) instanceof Map) {
                Map map1 = (Map) map.get(key);
                for (Object key1 : map1.keySet()) {
                    System.out.println(key1 + " : " + map1.get(key1));
                    if (map1.get(key1) instanceof Map) {
                        Map map2 = (Map) map1.get(key1);
                        for (Object key2 : map2.keySet()) {
                            System.out.println(key2 + " : " + map2.get(key2));
                        }
                    }
                }
            }
        }


    }
}