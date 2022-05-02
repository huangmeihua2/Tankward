package com.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x, y;
    private Direction tankDirection = Direction.DOWN;
    private final static int STEP = 5;

    public void setTankFireStrategy(TankFireStrategy tankFireStrategy) {
        this.tankFireStrategy = tankFireStrategy;
    }

    private TankFireStrategy tankFireStrategy = new DefaultTankFireStrategy();

    public GameModel getGameModel() {
        return gameModel;
    }

    private GameModel gameModel = null;
    private boolean isMoving;
    private boolean live = true;
    Rectangle tankRec = new Rectangle();

    public Group getGroup() {
        return group;
    }

    private Group group;
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

    public Tank(int x, int y, Direction tankDirection, GameModel gameModel, Group group, boolean isMoving) {
        this.x = x;
        this.y = y;
        this.tankDirection = tankDirection;
        this.gameModel = gameModel;
        this.group = group;
        this.isMoving = isMoving;
        this.tankRec.x = x;
        this.tankRec.y = y;
        this.tankRec.width = WIDHT;
        this.tankRec.height = HEIGHT;
    }

    public void paint(Graphics g) {
        // 当坦克的存活状态为false的时候，是不画出来的在快速迭代画布的时候就没有该坦克了。
        if (!live) {
            gameModel.tanks.remove(this);
            return;
        }
        switch (tankDirection) {
            case LEFT:
                if (this.group == Group.mainTank) g.drawImage(ResourceManager.tankL, x, y, null);
                else g.drawImage(ResourceManager.btankL, x, y, null);
                break;
            case RIGHT:
                if (this.group == Group.mainTank) g.drawImage(ResourceManager.tankR, x, y, null);
                else g.drawImage(ResourceManager.btankR, x, y, null);
                break;
            case UP:
                if (this.group == Group.mainTank) g.drawImage(ResourceManager.tankU, x, y, null);
                else g.drawImage(ResourceManager.btankU, x, y, null);
                break;
            case DOWN:
                if (this.group == Group.mainTank) g.drawImage(ResourceManager.tankD, x, y, null);
                else g.drawImage(ResourceManager.btankD, x, y, null);
                break;
        }
       /* if(x>800){
            x=50;
        }else  x +=5;*/
        moveTank();  //用于改变坦克图片的x,y的位置。
    }

    private void moveTank() {
        // 对于主坦克是只有按键按下去的时候，也就是里面的状态变量为真时才会移动,但是当抬起按键后又会被设为false。
        // 按下就移动对应的一个step，弹起后就会设定为false的。所以发射子弹是根据其按键速度来发射的。
        if (!isMoving) return;
        if (!live) return;
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
        if (this.group == Group.otherTank && random.nextInt(10) > 8) this.fire();
        if (this.group == Group.otherTank && random.nextInt(100) > 95) this.randDirection();
        boundCheck();
        this.tankRec.x = x;
        this.tankRec.y = y;
    }

    private void boundCheck() {
        if (x < 2) x = 3;
        if (y < 30) y = 32;
        if (x > GameModel.GAME_WIDTH - 45) x =  GameModel.GAME_WIDTH - 45;
        if (y > GameModel.GAME_HEIGHT - 45) y = GameModel.GAME_HEIGHT - 45;
    }

    private void randDirection() {
        this.tankDirection = Direction.values()[random.nextInt(4)];
    }

    public void fire() {
        tankFireStrategy.fire(this);
    }

    public void die() {
        this.live = false;
    }
}
