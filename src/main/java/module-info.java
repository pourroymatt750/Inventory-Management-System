module pourroy.c482 {
    requires javafx.controls;
    requires javafx.fxml;

    opens pourroy.c482 to javafx.fxml;
    opens pourroy.c482.model to javafx.fxml;

    exports pourroy.c482;
    exports pourroy.c482.model;
}