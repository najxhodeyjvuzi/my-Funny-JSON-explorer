package org.example.builder;

import org.example.composite.Component;
import org.example.patterns.PatternsFactory;
import org.example.patterns.abstractfractories.DotPatternsFacotry;
import org.example.patterns.abstractfractories.PokerFacePatternsFactory;
import org.example.printer.factory.PrinterFactory;
import org.example.printer.factory.RectanglePrinterFactory;

public class RectangleBuilder implements AbstractBuilder {

    public void setJsonRoot(Component root) {
        process.jsonRoot = root;
    }

    public void setPattern(String icon) {
        if (icon.equals("1")) {
            PatternsFactory dotPatternsFacotry = new DotPatternsFacotry();
            process.rectanglePattern = dotPatternsFacotry.createRectanglePattern("1","1");
        }
        if (icon.equals("2")) {
            PatternsFactory pokerFacePatternsFactory = new PokerFacePatternsFactory();
            process.rectanglePattern = pokerFacePatternsFactory.createRectanglePattern("2","2");
        }
    }

    public void setPrinter() {
        PrinterFactory rectanglePrinterFactory = new RectanglePrinterFactory();
        process.printer = rectanglePrinterFactory.createPrinter();
    }

    public Process getProcess() {
        return process;
    }
}
