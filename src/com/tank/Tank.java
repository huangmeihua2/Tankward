package com.tank;

import java.awt.*;

public class Tank {
    private int x, y;
    private Direction tankDirection = Direction.DOWN;
    private final static int STEP = 5;
    private TankFrame tankFrame = null;
    private boolean isMoving = true;
    private boolean live = true;
    public int WIDHT = ResourceManager.tankD.getWidth();
    public int HEIGHT = ResourceManager.tankD.getHeight();
    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setTankDirection(Direction tankDirection) {
        this.tankDirection = tankDirection;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getTankDirection() {
        return tankDirection;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tank(int x, int y, Direction tankDirection, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankDirection = tankDirection;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        // 当坦克的存活状态为false的时候，是不画出来的在快速迭代画布的时候就没有该坦克了。
        if(!live) {
            tankFrame.tanks.remove(this);
            return;
        }
        switch(tankDirection){
            case LEFT:
                g.drawImage(ResourceManager.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceManager.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankD,x,y,null);
                break;
        }
       /* if(x>800){
            x=50;
        }else  x +=5;*/
        moveTank();  //坦克是只有按键了，也就是里面的状态变量为真时才会移动。按下就移动对应的一个step。
    }

    private void moveTank() {
        if (!isMoving) return;
        switch (tankDirection) {
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

    }

    public void fire() {
        int bx = this.x;
        int by = this.y;
        switch (tankDirection){
            case LEFT:
                bx += WIDHT/2 - ResourceManager.bulletL.getWidth()/2;
                by += HEIGHT/2 -ResourceManager.bulletL.getHeight()/2;
                break;
            case RIGHT:
                bx += WIDHT/2 - ResourceManager.bulletR.getWidth()/2;
                by += HEIGHT/2 -ResourceManager.bulletR.getHeight()/2;
                break;
            case UP:
                bx += WIDHT/2 - ResourceManager.bulletU.getWidth()/2;
                by += HEIGHT/2 -ResourceManager.bulletU.getHeight()/2;
                break;
            case DOWN:
                bx += WIDHT/2 - ResourceManager.bulletD.getWidth()/2;
                by += HEIGHT/2 -ResourceManager.bulletD.getHeight()/2;
                break;
        }
        tankFrame.bulletList.add(new Bullet(bx, by, this.tankDirection,tankFrame));
    }

    public void die() {
        this.live = false;
    }
}
