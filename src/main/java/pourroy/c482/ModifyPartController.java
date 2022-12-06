package pourroy.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Modify Part Page that provides functionality for the application
 *
 * @author Matthew Pourroy
 * */
public class ModifyPartController implements Initializable {
    /**
     * Label for Machine ID/company in the Parts Table View
     * */
    @FXML
    private Label partIdNameLabel;

    /**
     * In-House Radio Button
     * */
    @FXML
    private RadioButton inHouseRadioButton;

    /**
     * Toggle group for the two radio buttons
     * Default setting is "In-House"
     * */
    @FXML
    private ToggleGroup togglePartType;

    /**
     * Outsourced Radio Button
     * */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * Part ID text field
     * */
    @FXML
    private TextField partIdField;

    /**
     * Part Name text field
     * */
    @FXML
    private TextField partNameField;

    /**
     * Part Inventory Level text field
     * */
    @FXML
    private TextField partInventoryField;

    /**
     * Part Price text field
     * */
    @FXML
    private TextField partPriceField;

    /**
     * Max number of parts allowed
     * */
    @FXML
    private TextField partMaxField;

    /**
     * Machine ID/company name field for the Parts Table View
     * In-House == Machine ID
     * Outsourced == Company name
     * */
    @FXML
    private TextField partIdNameField;

    /**
     * Minimum number of parts required in inventory
     * */
    @FXML
    private TextField partMinField;

    public void onCancelButton(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public void inHouseRadioButtonAction(ActionEvent actionEvent) {
    }

    public void outsourcedRadioButtonAction(ActionEvent actionEvent) {
    }

    public void saveButtonAction(ActionEvent actionEvent) {
    }

    /**
     * Initializes the controller function and populates the Table View
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }
}
