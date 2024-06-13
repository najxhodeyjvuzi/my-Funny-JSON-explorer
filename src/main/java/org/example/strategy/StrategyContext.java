package org.example.strategy;

import org.example.builder.Director;
import org.example.composite.Component;

public class StrategyContext {
    Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void excuteStrategy(Component root, String icon) {
        Director director = new Director(strategy.excuteCreateStrategy());
        director.direct(root, icon);
    }
}
