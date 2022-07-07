package com.dtanh.bomb.model;

import res.drawable.sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.dtanh.bomb.model.MapItem.SIZE;

public class BoomBang {
    private int x;
    private int y;
    public int lengh=2;
    private int lenghLeft = lengh;
    private int lenghRight = lengh;
    private int lenghUp = lengh;
    private int lenghDown = lengh;
    private int xBossDie;
    private int yBossDie;
    private String typeBossDie = "Ballom";
    private int imageIndex=0;

    public final Image[] BOOM_BANG = {
            new ImageIcon(getClass().getResource("/res/drawable/images/image/explosion_horizontal_left_last.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/explosion_horizontal_right_last.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/explosion_vertical_top_last.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/explosion_vertical_down_last.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/bomb_exploded.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/explosion_horizontal.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/explosion_horizontal.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/explosion_vertical.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/explosion_vertical.png")).getImage(),

    };

    public final Image[] BOSS_DIE={
            new ImageIcon(getClass().getResource("/res/drawable/images/image/balloom_dead.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/kondoria_dead.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/oneal_dead.png")).getImage(),
    };

    public final Image[] MAP_DESTROY ={
            new ImageIcon(getClass().getResource("/res/drawable/images/image/del_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/del_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/del_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/del_4.png")).getImage(),
    };

    public BoomBang(int x, int y, int lenghWave) {
        this.x = x;
        this.y = y;
        this.lenghLeft=lenghWave;
        this.lenghRight=lenghWave;
        this.lenghDown=lenghWave;
        this.lenghUp=lenghWave;
    }
    public boolean checkBoomToPlayer(ArrayList<BoomBang> arrBoomBang, Player player){
        for (int i = 0; i< arrBoomBang.size(); i++){
            Rectangle rectangle1= getRect(x+10,y+20).intersection(player.getRect());
            if (rectangle1.isEmpty()== false){
                return true;
            }
            for (int j=1;j<=lenghLeft;j++){
                int xRaw = x - j * SIZE + 10;
                int yRaw = y + 20;
                Rectangle rectangle=getRect(xRaw,yRaw).intersection(player.getRect());
                if (rectangle.isEmpty()==false){
                    return true;
                }
            }
            for (int j=1;j<=lenghRight;j++){
                int xRaw = x + j * SIZE + 10;
                int yRaw = y + 20;
                Rectangle rectangle=getRect(xRaw,yRaw).intersection(player.getRect());
                if (rectangle.isEmpty()==false){
                    return true;
                }
            }
            for (int j=1;j<=lenghUp;j++){
                int xRaw = x + 10;
                int yRaw = y - j * SIZE + 20;
                Rectangle rectangle=getRect(xRaw,yRaw).intersection(player.getRect());
                if (rectangle.isEmpty()==false){
                    return true;
                }
            }
            for (int j=1;j<=lenghDown;j++){
                int xRaw = x + 10;
                int yRaw = y + j * SIZE + 20;
                Rectangle rectangle=getRect(xRaw,yRaw).intersection(player.getRect());
                if (rectangle.isEmpty()==false){
                    return true;
                }
            }
        }
        return false;
    }

    public void checkBoomToBoss(ArrayList<Boss> arrBoss) {
        for (int i = 0; i < arrBoss.size(); i++) {
            try {
                Rectangle rectangle1= getRect(x+10,y+20).intersection(arrBoss.get(i).getRect());
                if (rectangle1.isEmpty()== false){
                    xBossDie= arrBoss.get(i).getX();
                    yBossDie=arrBoss.get(i).getY();
                    typeBossDie = checkTypeBossDie(arrBoss.get(i));
                    arrBoss.remove(i);
                    Clip clip= Sound.getSound(getClass().getResource("/res/drawable//sounds/bang_bang.wav"));
                    clip.start();
                }
                for (int j = 1; j <= lenghLeft; j++) {
                    int xRaw = x - j * SIZE + 10;
                    int yRaw = y + 20;
                    Rectangle rectangle0 = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangle0.isEmpty() == false) {
                        xBossDie= arrBoss.get(i).getX();
                        yBossDie=arrBoss.get(i).getY();
                        typeBossDie = checkTypeBossDie(arrBoss.get(i));
                        arrBoss.remove(i);
                        Clip clip= Sound.getSound(getClass().getResource("/res/drawable/sounds/bang_bang.wav"));
                        clip.start();
                    }
                }
                for (int j = 1; j <= lenghRight; j++) {
                    int xRaw = x + j * SIZE + 10;
                    int yRaw = y + 20;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        xBossDie= arrBoss.get(i).getX();
                        yBossDie=arrBoss.get(i).getY();
                        typeBossDie = checkTypeBossDie(arrBoss.get(i));
                        arrBoss.remove(i);
                        Clip clip= Sound.getSound(getClass().getResource("/res/drawable/sounds/bang_bang.wav"));
                        clip.start();
                    }
                }
                for (int j = 1; j <= lenghUp; j++) {
                    int xRaw = x + 10;
                    int yRaw = y - j * SIZE + 20;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        xBossDie= arrBoss.get(i).getX();
                        yBossDie=arrBoss.get(i).getY();
                        typeBossDie = checkTypeBossDie(arrBoss.get(i));
                        arrBoss.remove(i);
                        Clip clip= Sound.getSound(getClass().getResource("/res/drawable/sounds/bang_bang.wav"));
                        clip.start();

                    }
                }
                for (int j = 1; j <= lenghDown; j++) {
                    int xRaw = x + 10;
                    int yRaw = y + j * SIZE + 20;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        xBossDie= arrBoss.get(i).getX();
                        yBossDie=arrBoss.get(i).getY();
                        typeBossDie = checkTypeBossDie(arrBoss.get(i));
                        arrBoss.remove(i);
                        Clip clip= Sound.getSound(getClass().getResource("/res/drawable/sounds/bang_bang.wav"));
                        clip.start();
                    }
                }
            }catch (IndexOutOfBoundsException e){
            }
        }
    }

    public void checkBoomToBoom(ArrayList<Boom> arrBoom, ArrayList<Integer> timeBoom) {
        for (int i = 0; i < arrBoom.size(); i++) {
            Rectangle rectangle1= getRect(x+10,y+20).intersection(arrBoom.get(i).getRect());
            if (rectangle1.isEmpty()== false){
                timeBoom.set(i,0);
            }
            for (int j=1;j<=lenghLeft;j++) {
                int xRaw = x - j * SIZE + 10;
                int yRaw = y + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                if (rectangle.isEmpty() == false ) {
                    timeBoom.set(i, 0);
                }
            }
            for (int j=1;j<=lenghRight;j++) {
                int xRaw = x + j*SIZE + 10;
                int yRaw = y + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                if (rectangle.isEmpty() == false ) {
                    timeBoom.set(i, 0);
                }
            }
            for (int j=1;j<=lenghUp;j++) {
                int xRaw = x + 10;
                int yRaw = y - j*SIZE + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                if (rectangle.isEmpty() == false ) {
                    timeBoom.set(i, 0);
                }
            }
            for (int j=1;j<=lenghDown;j++) {
                int xRaw = x+10;
                int yRaw = y+j*SIZE+20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                if (rectangle.isEmpty() == false ) {
                    timeBoom.set(i, 0);
                }
            }
        }
    }

    public void draw(Graphics2D g2d,ArrayList<MapItem> arrMapItem, ArrayList<Boss> arrBoss){
        drawMid(g2d, arrMapItem);
        drawLeft(g2d, arrMapItem);
        drawRight(g2d, arrMapItem);
        drawUp(g2d, arrMapItem);
        drawDown(g2d, arrMapItem);
        if (xBossDie!=0 || yBossDie!=0) {
//            Image image = BOSS_DIE[imageIndex/50%BOSS_DIE.length];
            Image image = checkTypeBossDie_ToImage(typeBossDie);
            imageIndex++;
            g2d.drawImage(image, xBossDie, yBossDie,SIZE,SIZE, null);
        }
    }
    public void drawMid(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        g2d.drawImage(BOOM_BANG[4], x + 10, y + 20,SIZE,SIZE, null);
    }

    public Rectangle getRect(int xRaw,int yRaw) {
        Rectangle rectangle = new Rectangle(xRaw+5, yRaw+3, SIZE-10, SIZE-10);
        return rectangle;
    }

    public void drawLeft(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j = 1; j <= lenghLeft; j++) {
            int xRaw = x - j * SIZE + 10;
            int yRaw = y + 20;
            if (j==lenghLeft) {
                g2d.drawImage(BOOM_BANG[0], xRaw+5, yRaw-6,SIZE,SIZE, null);
            }else {
                g2d.drawImage(BOOM_BANG[5], xRaw, yRaw-6,SIZE,SIZE, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw,yRaw).intersection(arrMapItem.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4
                            || arrMapItem.get(i).bit == 5 ) {
                        arrMapItem.remove(i);
                        lenghLeft = lenghLeft - (lenghLeft - j);

                    } else if (arrMapItem.get(i).bit != 2 && arrMapItem.get(i).bit != 4
                            && arrMapItem.get(i).bit != 5 && arrMapItem.get(i).bit != 0) {
                        lenghLeft = lenghLeft - (lenghLeft - j);
                    }
                }
            }
        }
    }

    public void drawRight(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j=1;j<=lenghRight;j++) {
            int xRaw = x + j*SIZE + 10;
            int yRaw = y + 20;
            if (j==lenghRight) {
                g2d.drawImage(BOOM_BANG[1], xRaw-3, yRaw-6,SIZE,SIZE, null);
            }else {
                g2d.drawImage(BOOM_BANG[6], xRaw, yRaw-6,SIZE,SIZE, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw,yRaw).intersection(arrMapItem.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4
                            || arrMapItem.get(i).bit == 5 ) {
                        arrMapItem.remove(i);
                        lenghRight = lenghRight - (lenghRight - j);

                    } else if (arrMapItem.get(i).bit != 2 && arrMapItem.get(i).bit != 4
                            && arrMapItem.get(i).bit != 5 && arrMapItem.get(i).bit != 0) {
                        lenghRight = lenghRight - (lenghRight - j);
                    }
                }

            }
        }
    }

    public void drawUp(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j=1;j<=lenghUp;j++) {
            int xRaw = x + 10;
            int yRaw = y - j*SIZE + 20;
            if (j==lenghUp) {
                g2d.drawImage(BOOM_BANG[2], xRaw, yRaw+5,SIZE,SIZE, null);
            }else {
                g2d.drawImage(BOOM_BANG[7], xRaw, yRaw,SIZE,SIZE, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw,yRaw).intersection(arrMapItem.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4
                            || arrMapItem.get(i).bit == 5 ) {
                        arrMapItem.remove(i);
                        imageIndex++;
                        g2d.drawImage(MAP_DESTROY[imageIndex/20% MAP_DESTROY.length], arrMapItem.get(i).getX(), arrMapItem.get(i).getY(), SIZE,SIZE,null);
                        lenghUp = lenghUp - (lenghUp - j);

                    } else if (arrMapItem.get(i).bit != 2 && arrMapItem.get(i).bit != 4
                            && arrMapItem.get(i).bit != 5 && arrMapItem.get(i).bit != 0) {
                        lenghUp = lenghUp - (lenghUp - j);
                    }
                }
            }

        }
    }

    public void drawDown(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j=1;j<=lenghDown;j++) {
            int xRaw = x+10;
            int yRaw = y+j*SIZE+20;
            if (j==lenghDown) {
                g2d.drawImage(BOOM_BANG[3], xRaw, yRaw-3,SIZE,SIZE, null);
            }else {
                g2d.drawImage(BOOM_BANG[8], xRaw, yRaw,SIZE,SIZE, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw,yRaw).intersection(arrMapItem.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4
                            || arrMapItem.get(i).bit == 5 ) {
                        arrMapItem.remove(i);
                        imageIndex++;
                        g2d.drawImage(MAP_DESTROY[imageIndex/20% MAP_DESTROY.length], arrMapItem.get(i).getX(), arrMapItem.get(i).getY(),SIZE,SIZE,null);
                        lenghDown = lenghDown - (lenghDown - j);

                    } else if (arrMapItem.get(i).bit != 2 && arrMapItem.get(i).bit != 4
                            && arrMapItem.get(i).bit != 5 && arrMapItem.get(i).bit != 0) {
                        lenghDown = lenghDown - (lenghDown - j);
                    }
                }
            }
        }
    }

    private String checkTypeBossDie(Boss boss) {
        if (boss instanceof Balloom) return "Balloom";
        if (boss instanceof Kondoria) return "Kondoria";
        if (boss instanceof Oneal) return "Oneal";
        return "Kondoria";
    }

    private Image checkTypeBossDie_ToImage(String boss) {
        if (boss.equals("Balloom")) return BOSS_DIE[0];
        if (boss.equals("Kondoria")) return BOSS_DIE[1];
        if (boss.equals("Oneal")) return BOSS_DIE[2];
        return BOSS_DIE[0];
    }
}

