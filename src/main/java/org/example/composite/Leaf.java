package org.example.composite;

import java.util.ArrayList;

public class Leaf implements Component{
    private String value;
    private String key;
    private boolean isLast;
    private boolean isFirst = false;

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
