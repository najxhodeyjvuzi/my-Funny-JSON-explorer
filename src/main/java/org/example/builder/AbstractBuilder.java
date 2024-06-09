package org.example.builder;

import org.example.composite.Component;
import org.example.patterns.styles.RectanglePattern;
import org.example.patterns.styles.TreePattern;

public interface AbstractBuilder {
    public Process process = new Process();

    public void setJsonRoot(Component root);
    public void setPattern(String icon);
    public void setPrinter();

    public Process getProcess();
}
