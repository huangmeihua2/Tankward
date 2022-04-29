package TankProjectTest;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {
    @Test
    void test(){
        try {
            //BufferedImage bufferedImage = ImageIO.read(new File(""));
            BufferedImage image1 = ImageIO.read(getClass().getResource("/images/GoodTank1.png"));
            assertNotNull(image1);
            //类的加载
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
