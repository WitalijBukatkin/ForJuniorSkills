package util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ImageArray {

    public static ByteArrayInputStream ImageToByte(Object image) throws Exception{
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage((Image) image, null);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

    public static Image ByteToImage(InputStream image) throws Exception{
        return SwingFXUtils.toFXImage(ImageIO.read(image), null);
    }
}