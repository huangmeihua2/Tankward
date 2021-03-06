package com.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
    private Direction bulletDirection;
    private static final int STEP = 15;
    private boolean live = true;
    private Group group;
    private int WIDHT = ResourceManager.bulletD.getWidth();
    private int HIGHT = ResourceManager.bulletD.getHeight();
    TankFrame tankFrame = null;
    public Bullet(int x, int y, Direction direction,TankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.bulletDirection = direction;
        this.tankFrame = tankFrame;
        this.group = group;
    }
    //画每一颗子弹的时候都会调用这个
    public void paint(Graphics g) {
        if(!live){
            // 碰撞了的坦克就会被移除，那么下个循环中进行画布的时候，该坦克就不会被画出了。
            tankFrame.bulletList.remove(this);
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
        if(x<0||x>tankFrame.GAME_WIDTH||y<0||y>tankFrame.GAME_HEIGHT) live = false;
    }

    public void isCollideWithTank(Tank tank) {
        if(this.group == tank.getGroup()){
            return; //子弹不消灭己方
        }
        Rectangle bulletRec = new Rectangle(this.x,this.y,WIDHT,HIGHT);
        Rectangle tankRec = new Rectangle(tank.getX(),tank.getY(),tank.WIDHT,tank.HEIGHT);
        if(bulletRec.intersects(tankRec)){
            this.die();
            tank.die();
            tankFrame.explodeList.add(new Explode(x,y,tankFrame));
        }
    }

    private void die() {
        this.live = false;
    }
}
