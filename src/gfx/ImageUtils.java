package gfx;

import core.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageUtils {

    private static final int ALPHA_OPAQUE = 1;
    private static final int ALPHA_BIT_MASKED = 2;
    private static final int ALPHA_BLEND = 3;


    public static Image loadImage(String filePath){
        try {
            Image imageFromDisk = ImageIO.read(Objects.requireNonNull(ImageUtils.class.getResource(filePath)));
            BufferedImage compatibleImage = (BufferedImage) createCompatibleImage(
                    new Size(imageFromDisk.getWidth(null), imageFromDisk.getHeight(null)),
                    ALPHA_BIT_MASKED
            );

            Graphics2D graphics = compatibleImage.createGraphics();
            graphics.drawImage(imageFromDisk, 0, 0, null);

            graphics.dispose();
            return compatibleImage;
        } catch (IOException e){
            System.out.println("Could not load image from path: " + filePath);
        }
        return null;
    }

    public static Image createCompatibleImage(Size size, int transparency){
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        return graphicsConfiguration.createCompatibleImage(size.getWidth(), size.getHeight(), transparency);
    }
}
