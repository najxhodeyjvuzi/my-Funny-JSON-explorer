package org.example.printer;

import org.example.composite.Component;

import java.util.ArrayList;
import java.util.Stack;

public interface Printer {
    public void print(Component component, String compositeIcon, String leafIcon, int level, Stack<Integer> indent);
}
