package com.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManager {
    private static ResourceManager resourceManager = new ResourceManager();

    private ResourceManager() {

    }
    public static ResourceManager getResourceManager(){
        return  resourceManager;
    }
    public static BufferedImage tankL, tankR, tankU, tankD;
    public static BufferedImage btankL, btankR, btankU, btankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];
    static {
        try {
            tankU = ImageIO.read(ResourceManager.class.getResource("/images/GoodTank1.png"));
            btankU = ImageIO.read(ResourceManager.class.getResource("/images/BadTank1.png"));
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankR = ImageUtil.rotateImage(tankU,90);
            tankD = ImageUtil.rotateImage(tankU,180);
            btankL = ImageUtil.rotateImage(btankU,-90);
            btankR = ImageUtil.rotateImage(btankU,90);
            btankD = ImageUtil.rotateImage(btankU,180);
            bulletU = ImageIO.read(ResourceManager.class.getResource("/images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);
            for(int i = 0;i<16;i++){
                explodes[i] = ImageIO.read(ResourceManager.class.getResource("/images/e"+(i+1)+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
