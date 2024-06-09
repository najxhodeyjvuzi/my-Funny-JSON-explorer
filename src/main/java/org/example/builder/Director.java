package org.example.builder;

import org.example.composite.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class Director {

    private AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(AbstractBuilder builder) {
        this.builder = builder;
    }

    public void direct(Component root, String icon)  {
        builder.setJsonRoot(root);

        Yaml yaml = new Yaml(new Constructor((List.class)));
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/main/java/org/example/resources/IconFamily.yml");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Map<String, String>> list = yaml.load(inputStream);
        ArrayList<Icon> iconList = new ArrayList<>();

        for (Map<String, String> map : list) {
            iconList.add(new Icon(map.get("composite"), map.get("leaf")));
        }

        builder.setPattern(icon, iconList);
        builder.setPrinter();

        Process process = builder.getProcess();
        process.print();

    }


}
