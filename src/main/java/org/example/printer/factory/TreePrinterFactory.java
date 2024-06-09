package org.example.printer.factory;

import org.example.printer.Printer;
import org.example.printer.TreePrinter;

public class TreePrinterFactory implements PrinterFactory{
    @Override
    public Printer createPrinter() {
        return new TreePrinter();
    }
}
