package org.example.builder;

import org.example.composite.Component;
import org.example.patterns.PatternsFactory;
import org.example.patterns.abstractfractories.DotPatternsFacotry;
import org.example.patterns.abstractfractories.PokerFacePatternsFactory;
import org.example.printer.factory.PrinterFactory;
import org.example.printer.factory.TreePrinterFactory;

public class TreeBuilder implements AbstractBuilder{

    public void setJsonRoot(Component root) {
        process.jsonRoot = root;
    }

    public void setPattern(String icon) {
        if (icon.equals("1")) {
            PatternsFactory dotPatternsFacotry = new DotPatternsFacotry();
            process.treePattern = dotPatternsFacotry.createTreePattern("1","1");
        }
        if (icon.equals("2")) {
            PatternsFactory pokerFacePatternsFactory = new PokerFacePatternsFactory();
            process.treePattern = pokerFacePatternsFactory.createTreePattern("2","2");
        }
        // assert
    }

    public void setPrinter() {
        PrinterFactory treePrinterFactory = new TreePrinterFactory();
        process.printer = treePrinterFactory.createPrinter();
    }

    public Process getProcess() {
        return process;
    }
}
