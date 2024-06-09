package org.example.builder;

import org.example.composite.Component;
import org.example.patterns.styles.RectanglePattern;
import org.example.patterns.styles.TreePattern;
import org.example.printer.Printer;

import java.util.ArrayList;
import java.util.Stack;

public class Process {
    public Component jsonRoot;

    public TreePattern treePattern;
    public RectanglePattern rectanglePattern;

    public Printer printer;

    public void print() {

        Stack<Integer> stack = new Stack<>();
        printer.print(jsonRoot, treePattern!= null ? treePattern.composite : rectanglePattern.composite
                , treePattern != null ? treePattern.leaf : rectanglePattern.leaf, -1, stack);
    }

}
