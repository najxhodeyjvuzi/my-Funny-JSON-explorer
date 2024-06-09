package org.example.patterns;

import org.example.patterns.styles.RectanglePattern;
import org.example.patterns.styles.TreePattern;

public interface PatternsFactory {

    public TreePattern createTreePattern(String composite,String leaf);

    public RectanglePattern createRectanglePattern(String composite,String leaf);

}
