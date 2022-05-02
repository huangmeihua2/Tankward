package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class TankFrame extends Frame {


    // 用 窗口画笔的画出结果 置于图片上，就是用图片的画笔将窗口的画出结果画在图片上，然后再将图片画在窗口上。
    Image bufferImage = null;
    GameModel gameModel = new GameModel();
    public TankFrame() throws HeadlessException {
        // 在构造方法中创建了一个窗口，并设定了大小、标题、可见与否、背景颜色等，初始创建窗口的时候会调用paint方法。
        // 然后启用一个线程进行工作，在线程里不断调用repaint方法。
        //setLocation(400, 300);
        setSize(GameModel.GAME_WIDTH, GameModel.GAME_HEIGHT);
        setTitle("myTank war");
        setResizable(false);
        setBackground(Color.BLACK);
        setVisible(true);
        this.addKeyListener(new KeyMonitor()); //在对象上添加了监控，即在TankFrame对象上添加了监控。
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new Thread(new PaintThread()).start(); // 启用线程不断的重画，不断调用repaint，就会不断的调用paint方法。
    }

    // 在窗口上画画，该方法会在窗口Frame重新出现的时候，会自动重新调用。
    @Override
    public void paint(Graphics g) {
        gameModel.paint(g);
    }

    //在调用repaint时会先调用这个update，在update里面进行对实际paint的方法拦截，
    @Override
    public void update(Graphics g) {
        if (bufferImage == null) {
            bufferImage = this.createImage(GameModel.GAME_WIDTH, GameModel.GAME_HEIGHT);
        }
        Graphics imageGraphis = bufferImage.getGraphics();
        Color color = imageGraphis.getColor(); //前景色
        imageGraphis.setColor(Color.BLACK);
        imageGraphis.fillRect(0, 0, GameModel.GAME_WIDTH, GameModel.GAME_HEIGHT);
        imageGraphis.setColor(color);
        // 画在图片的画布上。
        paint(imageGraphis);
        // 将图片画在窗口上
        g.drawImage(bufferImage, 0, 0, null);
    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint();//不断调用repaint时，会先不断调用update方法，里面会进行paint方法的拦截，实际是不断调用repaint方法。
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter {
        boolean bl = false;
        boolean bu = false;
        boolean br = false;
        boolean bd = false;
        Tank myTank = gameModel.getMyTank();
        @Override
        public void keyPressed(KeyEvent e) {
            //当一个键被按下去的时候使用
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                default:
                    break;
            }
            setTankDirection();
            new Thread(()->new Audio("/audio/tank_move.wav").play()).start();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //当一个键被按下去松开的时候使用进行复原
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    new Thread(() ->new Audio("/audio/tank_fire.wav").play()).start();
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.setTankFireStrategy(new FourDirectionFireStrategy());
                    break;
                case KeyEvent.VK_SHIFT:
                    myTank.setTankFireStrategy(new DefaultTankFireStrategy());
                    break;
                default:
                    break;
            }
            setTankDirection();
        }

        //用于改变坦克的方向
        private void setTankDirection() {
            if (!bl && !bu && !br && !bd) {
                //没有按键，都是false,就停止移动,设为false。
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bl) myTank.setTankDirection(Direction.LEFT);
                if (bu) myTank.setTankDirection(Direction.UP);
                if (br) myTank.setTankDirection(Direction.RIGHT);
                if (bd) myTank.setTankDirection(Direction.DOWN);
            }
        }
    }

}
