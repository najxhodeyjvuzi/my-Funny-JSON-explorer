package org.example.builder;

public class Icon {
    private String composite;
    private String leaf;

    public Icon(String composite, String leaf) {
        this.composite = composite;
        this.leaf = leaf;
    }

    // Getters and setters
    public String getComposite() { return composite; }
    public void setComposite(String composite) { this.composite = composite; }

    public String getLeaf() { return leaf; }
    public void setLeaf(String leaf) { this.leaf = leaf; }

}