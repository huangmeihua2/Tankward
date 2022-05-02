package com.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
    private Direction bulletDirection;
    private static final int STEP = 15;
    private boolean live = true;
    Rectangle bulletRec = new Rectangle();
    public Group getGroup() {
        return group;
    }

    private Group group;
    private int WIDHT = ResourceManager.bulletD.getWidth();
    private int HIGHT = ResourceManager.bulletD.getHeight();
    GameModel gameModel = null;
    public Bullet(int x, int y, Direction direction,GameModel gameModel,Group group) {
        this.x = x;
        this.y = y;
        this.bulletDirection = direction;
        this.gameModel = gameModel;
        this.group = group;
        bulletRec.x = x;
        bulletRec.y = y;
        bulletRec.width = WIDHT;
        bulletRec.height = HIGHT;
    }
    //画每一颗子弹的时候都会调用这个
    public void paint(Graphics g) {
        if(!live){
            // 碰撞了的坦克就会被移除，那么下个循环中进行画布的时候，该坦克就不会被画出了。
            gameModel.bulletList.remove(this);
            return;
        }
        switch (bulletDirection){
            case LEFT:
                g.drawImage(ResourceManager.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD,x,y,null);
                break;
        }
       /* if(x>800){
            x=50;
        }else  x +=5;*/
        moveBullet(); //子弹是会一直移动的，根据坦克发射出的方向。
    }

    private void moveBullet() {
        switch (bulletDirection) {
            case LEFT:
                x -= STEP;
                break;
            case RIGHT:
                x += STEP;
                break;
            case UP:
                y -= STEP;
                break;
            case DOWN:
                y += STEP;
                break;
        }
        //超出边界了重新定义存活状态。
        if(x<0||x>gameModel.GAME_WIDTH||y<0||y>gameModel.GAME_HEIGHT) live = false;
        this.bulletRec.x = x;
        this.bulletRec.y = y;
    }

    public void isCollideWithTank(Tank tank) {
        if(this.group == tank.getGroup()){
            return; // 子弹不消灭己方
        }
        if(this.bulletRec.intersects(tank.tankRec)){
            this.die();
            tank.die();
            gameModel.explodeList.add(new Explode(tank.getX()+tank.WIDHT/2-Explode.WIDHT/2,tank.getY()+tank.HEIGHT-Explode.HIGHT,gameModel));
            new Thread(()->new Audio("/audio/explode.wav").play()).start();
        }
    }

    private void die() {
        this.live = false;
    }
}
