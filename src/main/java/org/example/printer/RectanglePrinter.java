package org.example.printer;

import org.example.composite.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class RectanglePrinter implements Printer {

    private int maxWidth = 0;
    private int lineCount = 0;
    private int currentLine = 0;

    private void calculateMaxWidth(Component component, int level) {
        if (component.getKey().equals("root")) {
            for (Component child : component.getChildren()) {
                calculateMaxWidth(child, level + 1);
            }
        } else {
            lineCount++;
            if (component.isComposite()) {
                maxWidth = Math.max(maxWidth, component.getKey().length() + 4 * level+3);
                for (Component child : component.getChildren()) {
                    calculateMaxWidth(child, level + 1);
                }
            } else if (component.isLeaf()) {
                maxWidth = Math.max(maxWidth, component.getKey().length() + component.getValue().length() + 2 + 4 * level+3);
            }
        }
    }

    private int printIndent(Stack<Integer> indent, String indentIcon,String padding) {
        int curIndent = 0;
        ArrayList<Integer> identList = new ArrayList<>(indent);
        for (int i = 0; i < identList.size(); i++) {
            if (identList.get(i) == 0 && currentLine == lineCount) {
                System.out.print("└─");
                continue;
            }
            System.out.print(padding.repeat(identList.get(i) - curIndent) + indentIcon);
            curIndent = identList.get(i);
        }
        return curIndent;
    }

    private void exactDraw(Component component, String compositeIcon, String leafIcon, int level, Stack<Integer> indent) {
        if (component.getKey().equals("root")) {
            for (Component child : component.getChildren()) {
                currentLine++;
                exactDraw(child, compositeIcon, leafIcon, level + 1, indent);
            }
            return;
        }

        if (currentLine == 1) {
            if (component.isComposite()) {
                System.out.print("┌─ " + compositeIcon + component.getKey() + " ─");
                System.out.print("─".repeat(maxWidth - component.getKey().length() - 4 * level));
                System.out.println("┐");
                indent.push(level);
                for (Component child : component.getChildren()) {
                    currentLine++;
                    exactDraw(child, compositeIcon, leafIcon, level + 1, indent);
                }
                indent.pop();
            }
            return;
        }

        if (currentLine == lineCount) {

            indent.push(level);
            int remainingIndent = level - printIndent(indent, "┴─","──");

            if (component.isLeaf()) {
                System.out.print("──".repeat(remainingIndent) + " "+ leafIcon + component.getKey() + ": " + component.getValue() + " ─");
                System.out.print("─".repeat(maxWidth - component.getKey().length() - component.getValue().length() - 2 - 4 * level));
                System.out.println("┘");
            }
            indent.pop();
            return;
        }

        int remainingIndent = level - printIndent(indent, "│","   ");

        if (component.isComposite()) {
            System.out.print("   ".repeat(remainingIndent) + "├─ " + compositeIcon + component.getKey() + " ─");
            System.out.print("─".repeat(maxWidth - component.getKey().length() - 4 * level));
            System.out.println("┤");
            indent.push(level);
            for (Component child : component.getChildren()) {
                currentLine++;
                exactDraw(child, compositeIcon, leafIcon, level + 1, indent);
            }
            indent.pop();
        } else if (component.isLeaf()) {
            System.out.print("   ".repeat(remainingIndent) + "├─ " + leafIcon + component.getKey() );
            if (component.getValue() != "")
                System.out.print(": " + component.getValue() + " ─");
            else
                System.out.print("   ─");
            System.out.print("─".repeat(maxWidth - component.getKey().length() - component.getValue().length() - 2 - 4 * level));
            System.out.println("┤");

        }

    }

    @Override
    public void print(Component component, String compositeIcon, String leafIcon, int level, Stack<Integer> indent) {

        /*
        ┌─ oranges ───────────────────────────────┐
        │  ├─ mandarin ───────────────────────────┤
        │  │  ├─ clementine ──────────────────────┤
        │  │  ├─ tangerine: cheap & juicy! ───────┤
        ├─ apples ────────────────────────────────┤
        └──┴─✩gala ───────────────────────────────┘
       */

        // calculate the max width of the rectangle needed to print the component
        calculateMaxWidth(component, level);


        exactDraw(component, compositeIcon, leafIcon, level, indent);


    }
}
