package dao.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sponsor extends NamedEntity {
    public Image logo;
    public String description;

    public ImageView getLogo() {
        ImageView imageView=new ImageView(logo);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        return imageView;
    }

    public String getDescription() {
        return description;
    }
}