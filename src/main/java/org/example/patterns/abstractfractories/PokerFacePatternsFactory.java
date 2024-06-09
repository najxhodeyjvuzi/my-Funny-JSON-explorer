package org.example.patterns.abstractfractories;

import org.example.patterns.PatternsFactory;
import org.example.patterns.styles.RectanglePattern;
import org.example.patterns.styles.RectangleStylePokerFaces;
import org.example.patterns.styles.TreePattern;
import org.example.patterns.styles.TreeStylePokerFaces;

public class PokerFacePatternsFactory implements PatternsFactory {

    public TreePattern createTreePattern(String composite,String leaf) {
        return new TreeStylePokerFaces(composite,leaf);
    }

    public RectanglePattern createRectanglePattern(String composite,String leaf) {
        return new RectangleStylePokerFaces(composite,leaf);
    }
}
