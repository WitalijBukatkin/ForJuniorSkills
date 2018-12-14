package view.main;
import entity.JuniorView;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import util.Query;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TabInfo {
    private Tab tab;

    TabInfo(){
        tab=new Tab("Info");
        VBox vBox=new VBox();
        vBox.setPadding(new Insets(10));
        tab.setContent(vBox);

        try {
            Query<JuniorView> juniorQuery = new Query<>(JuniorView.class);

            long juniorsCount=juniorQuery.getAll().size();
            double juniorsAVG=juniorQuery.getStream()
                    .mapToInt(e-> Period.between(e.birthday.toLocalDate(), LocalDate.now()).getYears())
                    .average().orElse(0);

            vBox.getChildren().addAll(
                    new Label("\nОбщее количество участников: " + juniorsCount),
                    new Label("\nСредний возраст участников: " + juniorsAVG)
            );

            vBox.getChildren().add(new Label("\nКоличество участников для каждой компетенции:"));

            Map<String, List<JuniorView>> integerListMap = juniorQuery.getStream()
                    .collect(Collectors.groupingBy(e -> e.competenceName));

            for (Map.Entry<String, List<JuniorView>> entry : integerListMap.entrySet())
                vBox.getChildren().add(new Label(entry.getKey() + " -> " + entry.getValue().size()));

            vBox.getChildren().add(new Label("\nСредний возраст участников по компетенции:"));

            for (Map.Entry<String, List<JuniorView>> entry : integerListMap.entrySet()) {
                double avg=entry.getValue().stream()
                        .mapToInt(e-> Period.between(e.birthday.toLocalDate(), LocalDate.now()).getYears())
                        .average().orElse(0);
                vBox.getChildren().add(new Label(entry.getKey() + " -> " + avg));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tab getTab() {
        return tab;
    }
}