package com.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x, y;
    private Direction tankDirection = Direction.DOWN;
    private final static int STEP = 5;
    private TankFrame tankFrame = null;
    private boolean isMoving;
    private boolean live = true;

    public Group getGroup() {
        return group;
    }

    private Group group ;
    public int WIDHT = ResourceManager.tankD.getWidth();
    public int HEIGHT = ResourceManager.tankD.getHeight();
    private Random random = new Random();
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

    public Tank(int x, int y, Direction tankDirection, TankFrame tankFrame, Group group,boolean isMoving) {
        this.x = x;
        this.y = y;
        this.tankDirection = tankDirection;
        this.tankFrame = tankFrame;
        this.group = group;
        this.isMoving = isMoving;
    }

    public void paint(Graphics g) {
        // 当坦克的存活状态为false的时候，是不画出来的在快速迭代画布的时候就没有该坦克了。
        if(!live) {
            tankFrame.tanks.remove(this);
            return;
        }
        switch(tankDirection){
            case LEFT:
                if(this.group==Group.mainTank)  g.drawImage(ResourceManager.tankL,x,y,null);
                else g.drawImage(ResourceManager.btankL,x,y,null);
                break;
            case RIGHT:
                if(this.group==Group.mainTank)  g.drawImage(ResourceManager.tankR,x,y,null);
                else g.drawImage(ResourceManager.btankR,x,y,null);
                break;
            case UP:
                if(this.group==Group.mainTank)  g.drawImage(ResourceManager.tankU,x,y,null);
                else g.drawImage(ResourceManager.btankU,x,y,null);
                break;
            case DOWN:
                if(this.group==Group.mainTank)  g.drawImage(ResourceManager.tankD,x,y,null);
                else g.drawImage(ResourceManager.btankD,x,y,null);
                break;
        }
       /* if(x>800){
            x=50;
        }else  x +=5;*/
        moveTank();
    }

    private void moveTank() {
        //对于主坦克是只有按键按下去的时候，也就是里面的状态变量为真时才会移动。
        // 按下就移动对应的一个step，弹起后就会设定为false的。所以发射子弹是根据其按键速度来发射的。
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
        // 对于设为true的所有坦克都是自动按频率调用开火，对于接受按键控制移动的坦克，则会根据按控件的速度来发射。
        if(this.group==Group.otherTank&&random.nextInt(10) > 8) this.fire();
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
        tankFrame.bulletList.add(new Bullet(bx, by, this.tankDirection,tankFrame,this.group)); //给对应的坦克对象创建子弹。
    }

    public void die() {
        this.live = false;
    }
}
