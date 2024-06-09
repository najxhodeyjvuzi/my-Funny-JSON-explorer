package org.example.composite;

import java.util.ArrayList;

public interface Component {
    public String getKey();
    public String getValue();

    public boolean getIsLast();
    public boolean getIsFirst();

    public boolean isLeaf();
    public boolean isComposite();

    public ArrayList<Component> getChildren();
    public void add(Component component);
    public void remove(Component component);

}
