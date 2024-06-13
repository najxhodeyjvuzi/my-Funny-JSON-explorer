package org.example.strategy;

import org.example.builder.AbstractBuilder;

public interface Strategy {
    AbstractBuilder excuteCreateStrategy();
}
