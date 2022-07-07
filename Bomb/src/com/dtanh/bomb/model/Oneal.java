package com.dtanh.bomb.model;

import javax.swing.*;
import java.awt.*;

import com.dtanh.bomb.manager.GameManager;
import com.dtanh.bomb.model.ai.*;

public class Oneal extends Boss{
    AI _ai = new AIMedium(gameManager.player, this);

    public final Image[] BOSS ={
            new ImageIcon(getClass().getResource("/res/drawable/images/image/oneal_left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/oneal_right1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/oneal_left2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/oneal_right3.png")).getImage(),
    };

    public Oneal(int x, int y, int orient, GameManager gameManager) {
        super(x, y, orient, gameManager);
    }

    @Override
    public void creatOrient() {
        int newOrient = _ai.calculateDirection();
        changeOrient(newOrient);
        image = BOSS[newOrient];
    }
}
