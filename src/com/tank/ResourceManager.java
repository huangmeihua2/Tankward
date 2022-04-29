package com.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManager {
    public static BufferedImage tankL, tankR, tankU, tankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[11];
    static {
        try {
            tankL = ImageIO.read(ResourceManager.class.getResource("/images/tankL.gif"));
            tankR = ImageIO.read(ResourceManager.class.getResource("/images/tankR.gif"));
            tankU = ImageIO.read(ResourceManager.class.getResource("/images/tankU.gif"));
            tankD = ImageIO.read(ResourceManager.class.getResource("/images/tankD.gif"));
            bulletL = ImageIO.read(ResourceManager.class.getResource("/images/bulletL.gif"));
            bulletR = ImageIO.read(ResourceManager.class.getResource("/images/bulletR.gif"));
            bulletU = ImageIO.read(ResourceManager.class.getResource("/images/bulletU.gif"));
            bulletD = ImageIO.read(ResourceManager.class.getResource("/images/bulletD.gif"));
            for(int i = 0;i<11;i++){
                explodes[i] = ImageIO.read(ResourceManager.class.getResource("/images/"+i+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}