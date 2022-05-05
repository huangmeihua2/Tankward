package Observers;

import com.tank.Tank;

public class EventFire {
    private Tank tank;

    public Tank getResource() {
        return tank;
    }

    public EventFire(Tank tank) {
        this.tank = tank;
    }
}
