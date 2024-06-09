package org.example.printer.factory;

import org.example.printer.Printer;
import org.example.printer.RectanglePrinter;

public class RectanglePrinterFactory implements PrinterFactory{
    @Override
    public Printer createPrinter() {
        return new RectanglePrinter();
    }
}
