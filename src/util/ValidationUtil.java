package util;

import javafx.scene.control.Control;
import javax.xml.bind.ValidationException;

public class ValidationUtil {
    public static final String isEmpty = " is empty!";

    public static void validator(boolean predicate, Control control, String message) throws ValidationException {
        if(predicate) {
            control.setStyle("-fx-background-color: red");
            throw new ValidationException(control.getId() + message);
        } else {
            control.setStyle("-fx-background-color: transparent");
        }
    }
}