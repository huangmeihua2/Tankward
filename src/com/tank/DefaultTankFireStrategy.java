package com.tank;

public class DefaultTankFireStrategy implements TankFireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX();
        int by = tank.getY();
        switch (tank.getTankDirection()){
            case LEFT:
                bx += tank.WIDHT/2 - ResourceManager.bulletL.getWidth()/2;
                by += tank.HEIGHT/2 -ResourceManager.bulletL.getHeight()/2;
                break;
            case RIGHT:
                bx += tank.WIDHT/2 - ResourceManager.bulletR.getWidth()/2;
                by += tank.HEIGHT/2 -ResourceManager.bulletR.getHeight()/2;
                break;
            case UP:
                bx += tank.WIDHT/2 - ResourceManager.bulletU.getWidth()/2;
                by += tank.HEIGHT/2 -ResourceManager.bulletU.getHeight()/2;
                break;
            case DOWN:
                bx += tank.WIDHT/2 - ResourceManager.bulletD.getWidth()/2;
                by += tank.HEIGHT/2 -ResourceManager.bulletD.getHeight()/2;
                break;
        }
        tank.getTankFrame().bulletList.add(new Bullet(bx, by, tank.getTankDirection(),tank.getTankFrame(),tank.getGroup())); //给对应的坦克对象创建子弹。

    }
}
