package org.example.jsonreader;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class JsonReader {

    public static Map jsonToMap(String filepath) {
        Gson gson = new Gson();
//        String filepath = "input/input.json";
        String json = "";
        try {
            json = new String(Files.readAllBytes(new File(filepath
            ).toPath()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map map = gson.fromJson(json, Map.class);
//        System.out.println(map);
//      考虑嵌套，逐级输出
//      这里考虑不知道有多少层级，所以用递归
//        printMap(map, 0);
        return map;
    }

    public static void printMap(Map map, int level) {
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.print(key + ": ");
            if (value instanceof Map) {
                System.out.println();
                printMap((Map) value, level + 1);
            } else {
                System.out.println(value);
            }
        }
    }
}
