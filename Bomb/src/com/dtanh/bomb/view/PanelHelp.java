package com.dtanh.bomb.view;

import res.drawable.sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.dtanh.bomb.view.Container.PANEL_MENU;
import static com.dtanh.bomb.view.Gui.H_FARME;
import static com.dtanh.bomb.view.Gui.W_FARME;

public class PanelHelp extends JPanel implements ActionListener {
    private JButton btnBack;
    private Container container;
    public static final String BACK="back";
    public final Icon[] icons={
            new ImageIcon(getClass().getResource("/res/drawable/images/skipButton1.png")),
            new ImageIcon(getClass().getResource("/res/drawable/images/skipButton2.png")),
    };
    public final Image[] images={
            new ImageIcon(getClass().getResource("/res/drawable/images/backgroundHelp.png")).getImage(),
    };
    public PanelHelp(Container container){
        setLayout(null);
        initComponents();
        initListener();
        this.container = container;
    }
    public void initComponents() {
        btnBack = new JButton(icons[0]);
        btnBack.setRolloverIcon(icons[1]);
        btnBack.setSize(icons[0].getIconWidth(),icons[0].getIconHeight());
        btnBack.setLocation(300,360);
        add(btnBack);
    }
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.drawImage(images[0],0,0,W_FARME,H_FARME,null);
    }

    public void initListener(){
        btnBack.addActionListener(this);
        btnBack.setActionCommand(BACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String run=e.getActionCommand();
        switch (run){
            case BACK:{
                Clip clip= Sound.getSound(getClass().getResource("/res/drawable/sounds/click.wav"));
                clip.start();
                container.showCard(PANEL_MENU);
                break;
            }
            default:
                break;
        }
    }
}
