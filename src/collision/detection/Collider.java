package collision.detection;

import com.tank.GameObject;

public interface Collider {

    Boolean collid(GameObject gameObject1, GameObject gameObject2);

}
