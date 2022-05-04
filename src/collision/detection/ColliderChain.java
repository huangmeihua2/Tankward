package collision.detection;

import com.tank.GameObject;
import com.tank.PropertyConfig;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {
    List<Collider> colliders = new LinkedList<>(); //其实就是对于两个GameObject对象，来一个个类型的碰撞检测，当检测正确的时候就停止责任链模式。
    PropertyConfig propertyConfig = PropertyConfig.getPropertyConfig();

    public ColliderChain() {
        Object[] test = propertyConfig.getColliderConfig();
        for (Object st : test) {
            String s = (String) st;
            Collider collider = null;
            try {
                collider = (Collider) Class.forName(s).getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            colliders.add(collider);
        }
    }

    @Override
    public Boolean collid(GameObject gameObject1, GameObject gameObject2) {
        for (int i = 0; i < colliders.size(); i++) {
            // 当已经检测出对应的碰撞类型了，就可以停止了，不继续往下验证碰撞类型了。
            if (!colliders.get(i).collid(gameObject1, gameObject2)) break;
        }
        return true;
    }
}
