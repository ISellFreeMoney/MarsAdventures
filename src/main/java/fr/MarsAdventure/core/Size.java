package fr.MarsAdventure.core;

public class Size {

    private int width;
    private int height;

    public Size(int width, int height){
        this.height = height; this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
