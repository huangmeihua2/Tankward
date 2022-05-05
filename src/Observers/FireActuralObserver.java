package Observers;

import com.tank.Tank;

public class FireActuralObserver implements Observer {
    @Override
    public void actionFire(EventFire eventFire) {
        Tank tank = eventFire.getResource();
        tank.fire();
    }
}
