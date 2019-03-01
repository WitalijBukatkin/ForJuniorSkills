package util;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;

import java.util.Set;

public class BindingUtil {

    public static void bindCheckBoxListToSet(ListView listView, Set set){
        listView.setCellFactory(CheckBoxListCell.forListView(e -> {
            BooleanProperty property = new SimpleBooleanProperty(set.contains(e));
            property.addListener((ob, o, n) -> {
                if(n) set.add(e);
                else set.remove(e);
            });
            return property;
        }));
    }

}