package org.example.strategy;

import org.example.builder.AbstractBuilder;
import org.example.builder.RectangleBuilder;

public class RectangleStrategy implements Strategy {

    @Override
    public AbstractBuilder excuteCreateStrategy() {
        return new RectangleBuilder();
    }
}
