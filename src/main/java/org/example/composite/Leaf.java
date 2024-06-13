package org.example.composite;

import org.example.iterator.ChildrenIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class Leaf implements Component{
    private String value;
    private String key;
    private boolean isLast;
    private boolean isFirst = false;

    public ChildrenIterator createChildrenIterator() {
        return new ChildrenIterator(this);
    }

    public Leaf(String key,String value,boolean isLast,boolean isFirst) {
        this.value = value;
        this.key = key;
        this.isLast = isLast;
        this.isFirst = isFirst;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public boolean getIsLast() {
        return isLast;
    }

    public boolean getIsFirst() {
        return isFirst;
    }

    public boolean isComposite() {
        return false;
    }

    public boolean isLeaf() {
        return true;
    }

    public void add (Component component) {
        return;
    }

    public void remove (Component component) {
        return;
    }

    public ArrayList<Component> getChildren() {
        return null;
    }

}
