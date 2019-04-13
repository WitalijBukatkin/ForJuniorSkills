package util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImageUtil {

    public static ImageView get(byte[] image){
        try(ByteArrayInputStream inputStream = new ByteArrayInputStream(image)){
            ImageView imageView = new ImageView(new Image(inputStream));
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            return imageView;
        } catch (Exception e){
            return new ImageView();
        }
    }

    public static byte[] save(ImageView image){
        try{
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ImageIO.write(SwingFXUtils.fromFXImage(image.getImage(), null), "png", outputStream);
                return outputStream.toByteArray();
            }
        } catch (Exception e){
            return null;
        }
    }
}
