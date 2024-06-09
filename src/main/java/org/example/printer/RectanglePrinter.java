package org.example.printer;

import org.example.composite.Component;

import java.util.ArrayList;
import java.util.Stack;

public class RectanglePrinter implements Printer{
    @Override
    public void print(Component component, String compositeIcon, String leafIcon, int level, Stack<Integer> indent){
        System.out.println("Printing Rectangle");
        System.out.println("Icon for composite: " + compositeIcon);
        System.out.println("Icon for leaf: " + leafIcon);
    }
}
