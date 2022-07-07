package com.dtanh.bomb.model;

import com.dtanh.bomb.manager.GameManager;
import com.dtanh.bomb.model.ai.AI;
import com.dtanh.bomb.model.ai.AILow;

import javax.swing.*;
import java.awt.*;

public class Kondoria extends Boss{
    private AI _ai = new AILow();

    public final Image[] BOSS = {
            new ImageIcon(getClass().getResource("/res/drawable/images/image/kondoria_left.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/kondoria_right.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/kondoria_up.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/kondoria_down.png")).getImage(),
    };
//    public final Image[] BOSS ={
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_left.png")).getImage(),
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_right.png")).getImage(),
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_up.png")).getImage(),
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_down.png")).getImage(),
//    };

    public Kondoria(int x, int y, int orient, GameManager gameManager) {
        super(x, y, orient, gameManager);
    }

    @Override
    public void creatOrient() {
        int newOrient = _ai.calculateDirection();
        changeOrient(newOrient);
        image = BOSS[newOrient];
    }
}
