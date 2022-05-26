package collision.detection;

import com.tank.GameObject;

import java.io.Serializable;

public interface Collider extends Serializable {

    Boolean collid(GameObject gameObject1, GameObject gameObject2);

}
