package com.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
       // new Thread(()->new Audio("/audio/war1.wav").loop()).start();
        // 敌方坦克不断打出自己的子弹，并可以相互消灭坦克，主坦克是一开始不动，敌方坦克一开始就动。
        PropertyConfig propertyConfig = PropertyConfig.getPropertyConfig();
        int TankCounts = Integer.parseInt((String) propertyConfig.getValue("intiallTankCount"));
        for(int i=0;i<TankCounts;i++){
            tankFrame.tanks.add(new Tank(100+i*100,100,Direction.DOWN,tankFrame,Group.otherTank,true));
        }
      /* while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }*/
    }

}
