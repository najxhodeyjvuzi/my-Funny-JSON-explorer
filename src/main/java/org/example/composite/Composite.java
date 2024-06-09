package org.example.composite;

import java.util.ArrayList;

public class Composite implements Component{

    private String key = "";
    private boolean isLast = false;
    private boolean isFirst = false;

    public Composite(String key, boolean isLast,boolean isFirst) {
        this.key = key;
        this.isLast = isLast;
        this.isFirst = isFirst;
    }

    private ArrayList<Component> values = new ArrayList<Component>();

    public String getKey() {
        return key;
    }

    public ArrayList<Component> getChildren() {
        return values;
    }

    public boolean getIsLast() {
        return isLast;
    }
    public boolean getIsFirst() {
        return isFirst;
    }

    public boolean isComposite() {
        return true;
    }

    public boolean isLeaf() {
        return false;
    }

    public void add(Component c) {
        values.add(c);
    }

    public void remove(Component c) {
        values.remove(c);
    }

    public String getValue() {
        return null;
    }

}
