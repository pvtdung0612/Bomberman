package com.dtanh.bomb.model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static com.dtanh.bomb.model.MapItem.SIZE;

public class Item extends Entity {
    private int bitItem;
    private Image image;
    private Random random=new Random();
    public final Image[] ITEM_IMAGE={
            new ImageIcon(getClass().getResource("/res/drawable/images/image/powerup_bombs.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/powerup_flames.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/powerup_speed.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/portal.png")).getImage(),
    };

    public Item(int x, int y, int bitItem) {
        int rd=random.nextInt(3);
        this.x = x;
        this.y = y;
        if (bitItem < 0) this.bitItem=rd;
        else this.bitItem = bitItem;
        this.image=ITEM_IMAGE[this.bitItem];
    }

    public int getBitItem() {
        return bitItem;
    }

    public void setBitItem(int bitItem) {
        this.bitItem = bitItem;
    }

    public Rectangle getRect(){
        Rectangle rectangle=new Rectangle(x+image.getWidth(null)/2,y+image.getHeight(null)/2,SIZE-30,SIZE-25);
        return rectangle;
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(image,x,y,SIZE-5,SIZE-5,null);
    }
}

