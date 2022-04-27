import java.awt.*;

public class Tank {
    private int x = 50, y = 50;
    private Direction tankDirection = Direction.DOWN;
    private final static int STEP = 5;

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
    private boolean isMoving = false;

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

    public Tank(int x, int y, Direction tankDirection) {
        this.x = x;
        this.y = y;
        this.tankDirection = tankDirection;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();//设置窗口的前景色，有默认的颜色，后面可以自己设置。
        g.setColor(Color.RED);
        g.fillOval(x, y, 50, 50);
        g.setColor(color);
       /* if(x>800){
            x=50;
        }else  x +=5;*/
        moveTank();
    }

    private void moveTank() {
        if (!isMoving) return;
        if (x > 800) {
            x = 50;
        } else if (x < 0) {
            x = 50;
        }
        if (y > 600) {
            y = 50;
        } else if (y < 0) {
            y = 50;
        }
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
    }
}
