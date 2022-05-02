package TankProjectTest;

import com.tank.PropertyConfig;
import com.tank.TankFireStrategy;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {
    private Random random = new Random();
    PropertyConfig propertyConfig = PropertyConfig.getPropertyConfig();

    @Test
    void test() {
        try {
            //BufferedImage bufferedImage = ImageIO.read(new File(""));
            BufferedImage image1 = ImageIO.read(getClass().getResource("/images/GoodTank1.png"));
            assertNotNull(image1);
            //类的加载
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String mainFire = (String) propertyConfig.getValue("mainTankFire");
        TankFireStrategy tankFireStrategy = (TankFireStrategy) Class.forName(mainFire).getDeclaredConstructor().newInstance();
        assertNotNull(tankFireStrategy);
    }
}
