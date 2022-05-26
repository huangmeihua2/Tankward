package Observers;

import java.io.Serializable;

public interface Observer extends Serializable {
    void actionFire(EventFire eventFire);
}
