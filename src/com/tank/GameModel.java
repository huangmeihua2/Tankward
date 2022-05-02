package com.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    public Tank getMyTank() {
        return myTank;
    }
    private Tank myTank = new Tank(200, 200, Direction.UP, this,Group.mainTank,false);
    List<Bullet> bulletList = new ArrayList<>();  // 也类似生产者消费者模式中的缓存区。
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodeList = new ArrayList<>();
    public final static int GAME_HEIGHT = 720;
    public final static int GAME_WIDTH = 1080;
    PropertyConfig propertyConfig = PropertyConfig.getPropertyConfig();
    int TankCounts = Integer.parseInt((String) propertyConfig.getValue("intiallTankCount"));
    public GameModel(){
        for(int i=0;i<TankCounts;i++){
            tanks.add(new Tank(100+i*100,100,Direction.DOWN,this,Group.otherTank,true));
        }
    }
    public void paint(Graphics g){
        //已经抽象出了一个坦克，但是把画坦克的方法放入到tank里面。
        g.setColor(Color.RED);
        g.drawString("子弹数量：" + bulletList.size(), 10, 60);
        g.drawString("敌方数量："+tanks.size(),10,100);
        myTank.paint(g);
        for (int i = 0; i < tanks.size(); i++) {
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
        }
        for(int i=0;i<bulletList.size();i++){
            for(int j=0;j<tanks.size();j++){
                bulletList.get(i).isCollideWithTank(tanks.get(j));
            }
        }
    }
}
