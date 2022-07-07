package com.dtanh.bomb.model;

import com.dtanh.bomb.manager.GameManager;
import com.dtanh.bomb.model.ai.AI;
import com.dtanh.bomb.model.ai.AILow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static com.dtanh.bomb.model.MapItem.SIZE;

public class Boss extends Entity {
    private int orient;
    protected Random random = new Random();
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    protected GameManager gameManager;
    private AI _ai;

    public final Image[] BOSS = {
            new ImageIcon(getClass().getResource("/res/drawable/images/image/balloom_left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/balloom_right1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/balloom_left2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/balloom_right3.png")).getImage(),
    };
//    public final Image[] BOSS ={
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_left.png")).getImage(),
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_right.png")).getImage(),
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_up.png")).getImage(),
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_down.png")).getImage(),
//    };

    public Boss(int x, int y, int orient, GameManager gameManager) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        this.gameManager = gameManager;
        _ai = new AILow();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void changeOrient(int newOrient) {
        orient = newOrient;
    }

    public void creatOrient() {
        int newOrient = _ai.calculateDirection();
        changeOrient(newOrient);
        image = BOSS[newOrient];
    }

    public void draw(Graphics2D g2d) {

        g2d.drawImage(image, x, y, SIZE, SIZE, null);

    }

    public boolean checkMoveBoom(ArrayList<Boom> arrBoom) {
        for (int i = 0; i < arrBoom.size(); i++) {
            Rectangle rectangle = getRect().intersection(arrBoom.get(i).getRect());
            if (rectangle.isEmpty() == false && arrBoom.get(i).isCheckBoom() == 0) {
                return false;
            }
        }
        return true;
    }

    public void moveBoss(ArrayList<MapItem> arrMapItem, ArrayList<Boom> arrBoom, int t) {
        int speed = 2;
        int xRaw = x;
        int yRaw = y;
        switch (orient) {
            case LEFT:
                xRaw -= speed;
                break;
            case RIGHT:
                xRaw += speed;
                break;
            case UP:
                yRaw -= speed;
                break;
            case DOWN:
                yRaw += speed;
            default:
        }
        int xRaw1 = x;
        int yRaw1 = y;
        x = xRaw;
        y = yRaw;
        boolean checkMoveBoss = checkMove(arrMapItem);
        boolean checkMoveBossBoom = checkMoveBoom(arrBoom);
        if (checkMoveBoss == true) {
            x = xRaw1;
            y = yRaw1;
        }
        if (checkMoveBossBoom == false) {
            x = xRaw1;
            y = yRaw1;
        }
        creatOrient();
    }

    public boolean checkMove(ArrayList<MapItem> arrMapItem) {
        for (MapItem mapItem : arrMapItem) {
            if (mapItem.bit == 5 || mapItem.bit == 1 || mapItem.bit == 2 || mapItem.bit == 3 ||
                    mapItem.bit == 4 || mapItem.bit == 6 || mapItem.bit == 7 || mapItem.bit == 8
                    || mapItem.bit == 9) {
                Rectangle rectangle = getRect().intersection(mapItem.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
        }
        return false;
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y + 15, SIZE - 10, SIZE - 5);
        return rectangle;
    }
}