package org.example.composite;

import org.example.iterator.ChildrenIterator;

import java.util.ArrayList;
import java.util.Iterator;

public interface Component {

    ChildrenIterator createChildrenIterator( );

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
