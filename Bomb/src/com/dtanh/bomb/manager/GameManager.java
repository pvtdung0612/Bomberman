package com.dtanh.bomb.manager;

import com.dtanh.bomb.model.*;
import res.drawable.sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import static com.dtanh.bomb.model.MapItem.SIZE;
import static com.dtanh.bomb.view.Gui.H_FARME;
import static com.dtanh.bomb.view.Gui.W_FARME;

public class GameManager {
    public long time_start = System.currentTimeMillis();
    public Player player;
    private ArrayList<MapItem> arrMapItem;
    private ArrayList<Boom> arrBoom;
    private ArrayList<Boss> arrBoss;
    private ArrayList<BoomBang> arrBoomBang;
    private ArrayList<Item> arrItem;
    public static final int TIME_BANG=120;
    public static final int TIME_WAVE=15;
    private int timeDie;
    private boolean checkWin;
    private Random random=new Random();
    private Clip clip1;
    private ArrayList<Integer> timeBoom;
    private ArrayList<Integer> timeWave;
    public final Image[] MY_IMAGE={
            new ImageIcon(getClass().getResource("/res/drawable/images/background.jpg")).getImage()
    };

    public boolean isCheckWin() {
        return checkWin;
    }

    public void setCheckWin(boolean checkWin) {
        this.checkWin = checkWin;
    }

    public void initGame(){
        Clip clip = Sound.getSound(getClass().getResource("/res/drawable/sounds/start.wav"));
        clip.start();
        clip1 =Sound.getSound(getClass().getResource("/res/drawable/sounds/soundGame.wav"));
        clip1.start();
        clip1.loop(100);
        timeBoom=new ArrayList<>();
        timeWave=new ArrayList<>();
        arrBoom=new ArrayList<>();
        arrBoss= new ArrayList<>();
        arrBoomBang = new ArrayList<>();
        arrItem=new ArrayList<>();
        player=new Player(SIZE,SIZE,Player.DOWN,1);
        arrMapItem =new ArrayList<>();
        readMap();
        initBoss();
        initItem();
    }

    public void initBoss(){
        // init Balloom
        for (int i=0;i<2;i++){
            int orient= random.nextInt(4);
            MapItem point= arrMapItem.get(random.nextInt(255));
            while (point.bit!=0) {
                point= arrMapItem.get(random.nextInt(255));
            }
            int xRaw=point.getX();
            int yRaw=point.getY();
            Boss boss=new Balloom(xRaw,yRaw,orient, this);
            arrBoss.add(boss);
        }
        // init Kondoria
        for (int i=0;i<2;i++){
            int orient= random.nextInt(4);
            MapItem point= arrMapItem.get(random.nextInt(255));
            while (point.bit!=0) {
                point= arrMapItem.get(random.nextInt(255));
            }
            int xRaw=point.getX();
            int yRaw=point.getY();
            Boss oneal = new Kondoria(xRaw, yRaw, orient, this);
            arrBoss.add(oneal);
        }
        // init Oneal
        for (int i=0;i<1;i++){
            int orient= random.nextInt(4);
            MapItem point= arrMapItem.get(random.nextInt(255));
            while (point.bit!=0) {
                point= arrMapItem.get(random.nextInt(255));
            }
            int xRaw=point.getX();
            int yRaw=point.getY();
            Boss oneal = new Oneal(xRaw, yRaw, orient, this);
            arrBoss.add(oneal);
        }
    }

    public void initItem(){
        ArrayList<MapItem> arrMapItemHaveItem = new ArrayList<>();
        for (int i = 0; i< arrMapItem.size(); i++) {
            int show = random.nextInt(100) + 1;
            if (show > 80 && (arrMapItem.get(i).bit == 2
                    || arrMapItem.get(i).bit == 4 || arrMapItem.get(i).bit == 5)) {
                arrMapItemHaveItem.add(arrMapItem.get(i));
            }
        }
        int rd_portal = random.nextInt(arrMapItemHaveItem.size());
        System.out.println("init item in GameManager have: " + arrMapItemHaveItem.size());
        for (int i = 0; i < arrMapItemHaveItem.size(); i++) {
            int xRaw = arrMapItemHaveItem.get(i).getX();
            int yRaw = arrMapItemHaveItem.get(i).getY();
            Item item = null;
            if (i == rd_portal) item = new Item(xRaw, yRaw, 3);
            else item = new Item(xRaw, yRaw, -1);
            arrItem.add(item);
        }
    }

    public void movePlayer(int newOrient){
        player.changeOrient(newOrient);
        player.move(arrMapItem,arrBoom,1);
        player.moveItem(arrItem);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(MY_IMAGE[0],0,0,W_FARME,H_FARME,null);
        try {
            for (Boom boom : arrBoom) {
                boom.draw(g2d);
            }
            for (BoomBang boomBang : arrBoomBang) {
                boomBang.draw(g2d, arrMapItem, arrBoss);
            }
            for (Item item:arrItem){
                item.draw(g2d);
            }
            for (MapItem mapItem : arrMapItem) {
                mapItem.draw(g2d);
            }

            for (Boss boss : arrBoss) {
                boss.draw(g2d);
            }
            player.draw(g2d);
        }catch (ConcurrentModificationException e){

        }
    }

    public void myPlayerBoom(int t){
        Boom boom= player.DatBoom(arrBoom);
        if (arrBoom.size()<player.getSoBoom()){
            arrBoom.add(boom);
            Clip clip = Sound.getSound(getClass().getResource("/res/drawable/sounds/set_boom.wav"));
            clip.start();
            timeBoom.add(t);
        }
    }

    public boolean AI(int t){
        for (int i=arrBoss.size()-1;i>=0;i--){
            arrBoss.get(i).moveBoss(arrMapItem,arrBoom,t);
        }
        for (int i=0;i<arrBoom.size();i++){
            if (t-timeBoom.get(i) >=TIME_BANG){
                BoomBang boomBang = arrBoom.get(i).boomBang();
                arrBoom.remove(i);
                Clip clip=Sound.getSound(getClass().getResource("/res/drawable/sounds/boom_bang.wav"));
                clip.start();
                arrBoomBang.add(boomBang);
                timeBoom.remove(i);
                try {
                    boomBang.checkBoomToBoom(arrBoom, timeBoom);
                }catch (IndexOutOfBoundsException e){
                }
                timeWave.add(t);
            }
        }
        for (int i = 0; i< arrBoomBang.size(); i++){
            arrBoomBang.get(i).checkBoomToBoss(arrBoss);
            if (t-timeWave.get(i)>=TIME_WAVE){
                arrBoomBang.remove(i);
                timeWave.remove(i);
            }
        }
        if (player.checkDieToBoss(arrBoss) == true){
            clip1.stop();
            setCheckWin(false);
            return false;
        }
        for (int i = 0; i< arrBoomBang.size(); i++){
            if(arrBoomBang.get(i).checkBoomToPlayer(arrBoomBang,player)==true){
                clip1.stop();
                timeDie=t;
                setCheckWin(false);
                return false;
            }
        }
        if (arrBoss.isEmpty() || player.isGetPortal == true){
            player.isGetPortal = false;
            setCheckWin(true);
            clip1.stop();
            return false;
        }
        return true;
    }

    public void readMap(){
        int intLine=0;
        try {
            String path = getClass().getResource("/res/data/map2.txt").getPath();
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line= br.readLine();
            while (line!=null){
                for (int i=0;i<line.length();i++){
                    arrMapItem.add(new MapItem(i*SIZE,intLine*SIZE,Integer.parseInt(String.valueOf(line.charAt(i)))));
                }
                line= br.readLine();
                intLine++;
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


