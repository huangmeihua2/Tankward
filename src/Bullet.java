import java.awt.*;

public class Bullet {
    private int x,y;
    private Direction bulletDirection;
    private static final int STEP = 10;
    private static final int WIDTH = 10,HEIGHT=10;
    public Bullet(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.bulletDirection = direction;
    }
    public void paint(Graphics g) {
        Color color = g.getColor();//设置窗口的前景色，有默认的颜色，后面可以自己设置。
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
       /* if(x>800){
            x=50;
        }else  x +=5;*/
        moveTank();
    }

    private void moveTank() {
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
    }
}
