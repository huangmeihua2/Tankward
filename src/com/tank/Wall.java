package com.tank;

import java.awt.*;

public class Wall extends GameObject {
    private int width;
    private int height;
    private int index;
    public Rectangle wallRec = new Rectangle();
    private GameModel gameModel;
    public Wall(int x,int y,GameModel gameModel,int index) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
        this.index = index;
        width = ResourceManager.walls[index].getWidth();
        height = ResourceManager.walls[index].getHeight();
        wallRec.x = x;
        wallRec.y = y;
        wallRec.width = width;
        wallRec.height = height;
        gameModel.gameObjectList.add(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceManager.walls[index],x,y,null);
    }
}
