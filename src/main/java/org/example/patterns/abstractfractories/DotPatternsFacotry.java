package org.example.patterns.abstractfractories;

import org.example.patterns.PatternsFactory;
import org.example.patterns.styles.*;

public class DotPatternsFacotry implements PatternsFactory {

    @Override
    public RectanglePattern createRectanglePattern(String composite,String leaf) {
        return new RectangleStyleDots(composite,leaf);
    }

    public TreePattern createTreePattern(String composite,String leaf) {
        return new TreeStyleDots(composite,leaf);
    }

}
