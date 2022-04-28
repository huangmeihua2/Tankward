import java.awt.*;

public class Bullet {
    private int x,y;
    private Direction bulletDirection;
    private static final int STEP = 10;
    private static final int WIDTH = 10,HEIGHT=10;
    private boolean live = true;
    TankFrame tankFrame = null;
    public Bullet(int x, int y, Direction direction,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.bulletDirection = direction;
        this.tankFrame = tankFrame;
    }
    //画每一颗子弹的时候都会调用这个
    public void paint(Graphics g) {
        if(!live){
            tankFrame.bulletList.remove(this);
            return;
        }
        Color color = g.getColor();//设置窗口的前景色，有默认的颜色，后面可以自己设置。
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
       /* if(x>800){
            x=50;
        }else  x +=5;*/
        moveBullet();
    }

    private void moveBullet() {
        switch (bulletDirection) {
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
        if(x<0||x>tankFrame.GAME_WIDTH||y<0||y>tankFrame.GAME_HEIGHT) live = false; //超出边界了重新定义存活状态。
    }
}
