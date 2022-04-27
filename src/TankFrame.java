import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x = 50,y=50;
    public final static int HEIGHT=600;
    public final static int WIDTH=800;
    // 用 窗口画笔的画出结果 置于图片上，就是用图片的画笔将窗口的画出结果画在图片上，然后再将图片画在窗口上。
    Image bufferImage = null;

    public TankFrame() throws HeadlessException {
        // 创建了一个窗口，并设定了大小、标题、可见与否、背景颜色等。
        this.setLocation(400,300);
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("TankWar");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.GREEN);
        this.addKeyListener(new KeyMonitor());
        setVisible(true);
        new Thread(new PaintThread()).start(); // 使用线程不断的重画
    }
    //在窗口上画画，该方法会在窗口Frame重新出现的时候，会自动重新调用。
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();//设置窗口的前景色，有默认的颜色，后面可以自己设置。
        g.setColor(Color.RED);
        g.fillOval(x,y,50,50);
        g.setColor(color);
        if(y>600){
            y=50;
        }else  y +=5;
    }
    //在调用repaint时会先调用这个update，在update里面进行对实际paint的方法拦截，
    @Override
    public void update(Graphics g) {
        if(bufferImage==null){
            bufferImage = this.createImage(WIDTH,HEIGHT);
        }
        Graphics imageGraphis = bufferImage.getGraphics();
        Color color = imageGraphis.getColor(); //前景色
        imageGraphis.setColor(Color.GREEN);
        imageGraphis.fillRect(0,0,WIDTH,HEIGHT);
        imageGraphis.setColor(color);
        //画在图片的画布上。
        paint(imageGraphis);
        // 将图片画在窗口上
        g.drawImage(bufferImage,0,0,null);
    }

    private class PaintThread implements Runnable{
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }
}
