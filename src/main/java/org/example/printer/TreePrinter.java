package org.example.printer;

import org.example.composite.Component;

import java.util.ArrayList;
import java.util.Stack;

public class TreePrinter implements Printer {

    private int printIndent(Stack<Integer> indent) {
        ArrayList<Integer> indentList = new ArrayList<>(indent);
        int curIndent = 0;
        for (int i = 0; i < indentList.size(); i++) {
            System.out.print("   ".repeat(indentList.get(i)-curIndent) + "│");
            curIndent = indentList.get(i);
        }
        return curIndent;
    }

    @Override
    public void print(Component component, String compositeIcon, String leafIcon, int level, Stack<Integer> indent) {
        if (!component.getKey().equals("root")) {
            int remainingIndent = level - printIndent(indent);
            if (component.isLeaf()) {
                if (component.getIsLast()) {
                    System.out.print("   ".repeat(remainingIndent) + "└─" + leafIcon + component.getKey());
                    String value = component.getValue();
                    if (value != "") System.out.println(": " + value);
                    else System.out.println();
                } else {
                    System.out.print("   ".repeat(remainingIndent) + "├─" + leafIcon + component.getKey());
                    String value = component.getValue();
                    if (value != "") System.out.println(": " + value);
                    else System.out.println();
                }
            }

            if (component.isComposite()) {

                if (component.getIsLast()) {
                    System.out.println("   ".repeat(remainingIndent) + "└─" + compositeIcon + component.getKey());
                    for (Component child : component.getChildren()) {
                        print(child, compositeIcon, leafIcon, level + 1,indent);
                    }
                } else {
                    indent.push(level);
                    System.out.println("   ".repeat(remainingIndent) + "├─" + compositeIcon + component.getKey());
                    for (Component child : component.getChildren()) {
                        print(child, compositeIcon, leafIcon, level + 1,indent);
                    }
                    indent.pop();
                }
            }


        } else {
            for (Component child : component.getChildren()) {
                print(child, compositeIcon, leafIcon, level + 1,indent);
            }
        }
    }
}
