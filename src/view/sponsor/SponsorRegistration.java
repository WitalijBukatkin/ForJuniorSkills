package view.sponsor;

import entity.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SponsorRegistration extends BaseSponsorPage{
    @Override
    protected void onClickApply() throws Exception {
        sponsor = new Sponsor();
        setSponsor();
        new Query<>(Sponsor.class)
                .insert(sponsor);
    }
}