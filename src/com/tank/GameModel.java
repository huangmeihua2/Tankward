package com.tank;

import collision.detection.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.对于tankFrame来说是充当门面的角色，它会将客户端发来的paint的请求发送到各个子系统（tank\explode\bullet）中由各个子系统来去执行。
 * 2.对于tank\explode\bullet来说是调停者。
 */
public class GameModel {
    private Tank myTank = new Tank(200, 200, Direction.UP, this, Group.mainTank, false);
    //public List<Bullet> bulletList = new ArrayList<>();  // 也类似生产者消费者模式中的缓存区。
    // public List<Tank> tanks = new ArrayList<>();
    // public List<Explode> explodeList = new ArrayList<>();
    public List<GameObject> gameObjectList = new ArrayList<>();
    public ColliderChain colliderChain = new ColliderChain();
    public final static int GAME_HEIGHT = 720;
    public final static int GAME_WIDTH = 1080;
    PropertyConfig propertyConfig = PropertyConfig.getPropertyConfig();
    int TankCounts = Integer.parseInt((String) propertyConfig.getValue("intiallTankCount"));

    public GameModel() {
        for (int i = 0; i < TankCounts; i++) {
            gameObjectList.add(new Tank(100 + i * 160, 30, Direction.DOWN, this, Group.otherTank, true));
        }
        for(int i = 0; i<7;i++ ){
            new Wall(122*(i+1),100,this,0);
        }
        for(int i = 0; i<7;i++ ){
            new Wall(122*(i+1),451,this,0);
        }
        new Wall(122,342,this,1);
        new Wall(622,342,this,1);
        new Wall(391,344,this,2);
        new Wall(891,344,this,2);
    }

    public Tank getMyTank() {
        return myTank;
    }

    public void paint(Graphics g) {
        //已经抽象出了一个坦克，但是把画坦克的方法放入到tank里面。
        g.setColor(Color.RED);
        g.drawString("子弹数量：" + gameObjectList.size(), 10, 60);
        g.drawString("敌方数量：" + gameObjectList.size(), 10, 100);
        myTank.paint(g);
        for(int i = 0;i<gameObjectList.size();i++){
            gameObjectList.get(i).paint(g);
        }
       /* for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //当没有按开火键的时候，这子弹容器里面是没有子弹的，不会执行。
        for (int i = 0; i < bulletList.size(); i++) {
            //当采用迭代器进行遍历的时候，由于记录了状态，当其他地方进行删除修改操作的时候，不允许的。
            Bullet cur = bulletList.get(i);
            cur.paint(g);
        }
        for (int i = 0; i < explodeList.size(); i++) {
            //当采用迭代器进行遍历的时候，由于记录了状态，当其他地方进行删除修改操作的时候，不允许的。
            explodeList.get(i).paint(g);
        }*/
        for (int i = 0; i < gameObjectList.size(); i++) {
            for (int j = i+1; j < gameObjectList.size(); j++) {
                // 两两碰撞检测。
                colliderChain.collid(gameObjectList.get(i),gameObjectList.get(j));
            }
        }
    }
}
