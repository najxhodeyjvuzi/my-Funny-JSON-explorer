package org.example;

import org.example.jsonReader.jsonReader;
import picocli.CommandLine;

import java.util.Map;

//public class Main {
//    public static void main(String[] args) {
//        printHelloWorld();
//    }
//
//    public static void printHelloWorld() {
//        jsonReader jsonReader = new jsonReader();
//        Map map = jsonReader.jsonToMap();
//    }
//}

@CommandLine.Command(name = "FJE", mixinStandardHelpOptions = true, version = "FJE 1.0",
        description = "FJE - JSON to Map")
public class FJE implements Runnable{

    @CommandLine.Option(names = {"-f", "--file"}, description = "Path to JSON file", required = true)
    private String file;

    @CommandLine.Option(names = {"-s", "--style"}, description = "Style of output", required = true)
    private String style;

    @CommandLine.Option(names = {"-i", "--icon"}, description = "Icon family to use", required = true)
    private String icon;

    @CommandLine.Option(names = {"-o", "--output"}, description = "Output file", required = false)
    private String output;

    @Override
    public void run() {
        jsonReader jsonReader = new jsonReader();
        Map map = jsonReader.jsonToMap(file);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FJE()).execute(args);
        System.exit(exitCode);
    }
}