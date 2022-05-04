package com.tank;

import java.awt.*;

public class Explode extends GameObject{
    private  int step = 0;
    private boolean live = true;
    public static final int WIDHT = ResourceManager.explodes[0].getWidth();
    public static final int HIGHT = ResourceManager.explodes[0].getHeight();
    GameModel gameModel = null;
    public Explode(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
    }
    //画每一颗子弹的时候都会调用这个
    @Override
    public void paint(Graphics g) {
        // 每调用一次会画一张，连续画出10张,每一次循环步步调用。一次只能画一张，若同时在一个位置画出多张，会重合。
       g.drawImage(ResourceManager.explodes[step++],x,y,null);
       // 当画出全部的时候，又重新画。
       if(step>10) gameModel.gameObjectList.remove(this);
    }
}

