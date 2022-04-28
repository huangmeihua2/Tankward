package com.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        for(int i=0;i<6;i++){
            tankFrame.tanks.add(new Tank(100+i*50,100,Direction.DOWN,tankFrame));
        }
      /* while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }*/
    }
}
