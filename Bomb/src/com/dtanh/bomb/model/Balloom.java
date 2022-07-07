package com.dtanh.bomb.model;

import com.dtanh.bomb.manager.GameManager;
import com.dtanh.bomb.model.ai.AI;
import com.dtanh.bomb.model.ai.AILow;
import com.dtanh.bomb.model.ai.AIMedium;

import javax.swing.*;
import java.awt.*;

public class Balloom extends Boss {
    private AI _ai = new AILow();

    public final Image[] BOSS = {
            new ImageIcon(getClass().getResource("/res/drawable/images/image/balloom_left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/balloom_right1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/balloom_left2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/balloom_right3.png")).getImage(),
    };

    public Balloom(int x, int y, int orient, GameManager gameManager) {
        super(x, y, orient, gameManager);
    }

    @Override
    public void creatOrient() {
        int newOrient = _ai.calculateDirection();
        changeOrient(newOrient);
        image = BOSS[newOrient];
    }
}
