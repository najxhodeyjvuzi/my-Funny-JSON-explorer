package org.example;

import org.example.builder.AbstractBuilder;
import org.example.builder.Director;
import org.example.builder.RectangleBuilder;
import org.example.builder.TreeBuilder;
import org.example.composite.Component;
import org.example.composite.Composite;
import org.example.composite.Map2Component;
import org.example.jsonreader.JsonReader;
import org.example.strategy.RectangleStrategy;
import org.example.strategy.Strategy;
import org.example.strategy.StrategyContext;
import org.example.strategy.TreeStrategy;
import picocli.CommandLine;

import java.util.Map;

@CommandLine.Command(name = "FJE", mixinStandardHelpOptions = true, version = "FJE 1.0",
        description = "FJE - JSON to Map")
public class FJE implements Runnable{

    @CommandLine.Option(names = {"-f", "--file"}, description = "Path to JSON file", required = true)
    private String file;

    @CommandLine.Option(names = {"-s", "--style"}, description = "Style of output", required = true)
    private String style;

    @CommandLine.Option(names = {"-i", "--icon"}, description = "Icon family to use", required = true)
    private String icon;

    @Override
    public void run() {
        JsonReader jsonReader = new JsonReader();
        Map map = jsonReader.jsonToMap(file);

        Component root = new Composite("root",true,true);

        // 使用递归构建树形结构
        Map2Component.buildComponent(root, map);

        StrategyContext context = null;
        if (style.equals("tree")) context = new StrategyContext(new TreeStrategy());
        if (style.equals("rectangle")) context = new StrategyContext(new RectangleStrategy());

        context.excuteStrategy(root,icon);

//        Director director = new Director(builder);
//        director.direct(root,icon);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FJE()).execute(args);
        System.exit(exitCode);
    }
}