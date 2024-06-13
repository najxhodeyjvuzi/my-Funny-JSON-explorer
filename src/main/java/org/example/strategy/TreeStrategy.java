package org.example.strategy;

import org.example.builder.AbstractBuilder;
import org.example.builder.TreeBuilder;

public class TreeStrategy implements Strategy{
    @Override
    public AbstractBuilder excuteCreateStrategy() {
        return new TreeBuilder();
    }
}
