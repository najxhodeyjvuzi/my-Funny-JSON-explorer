package org.example.iterator;

import org.example.composite.Component;

public class ChildrenIterator implements Iterator{
    private final Component component;
    private int currentComponent = 0;

    public ChildrenIterator(Component com){
        this.component = com;
    }

    @Override
    public boolean hasNextChild() {
        return currentComponent < component.getChildren().size();
    }

    @Override
    public Component nextChild() {
        return component.getChildren().get(currentComponent++);
    }
}
