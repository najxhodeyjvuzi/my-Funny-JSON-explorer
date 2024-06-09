package org.example.builder;

import org.example.composite.Component;

public class Director {

    private AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(AbstractBuilder builder) {
        this.builder = builder;
    }

    public void direct(Component root, String icon) {
        builder.setJsonRoot(root);
        builder.setPattern(icon);
        builder.setPrinter();

        Process process = builder.getProcess();
        process.print();

    }


}
