package com.dtanh.bomb.model;

import java.awt.*;

public abstract class Entity {
    public static final int SIZE=45;
    protected int x;
    protected int y;
    protected Image image;

    public abstract void draw(Graphics2D g2d);
}
