package org.example.iterator;

import org.example.composite.Component;

public interface Iterator {
    boolean hasNextChild();
    Component nextChild();
}
