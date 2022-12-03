package pourroy.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public Label TheLabel;
    public int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }

    public void OnButtonClicked(ActionEvent actionEvent) {
        System.out.println("I am clicked!!");
        TheLabel.setText("You clicked the BUTTON: " + count++);
    }
}