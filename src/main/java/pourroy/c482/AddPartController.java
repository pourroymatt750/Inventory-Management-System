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
 * Controller for the Add Part Page that provides functionality for the application
 *
 * @author Matthew Pourroy
 * */
public class AddPartController implements Initializable {
    /**
     * Machine ID
     * */
    @FXML
    private Label machineId;

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
     * Machine ID/company name label for the part
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

    /**
     * Saves the new added part into the inventory and displays in the Home Page
     *
     * @param actionEvent Save button action
     * @throws IOException from FXMLLoader
     * */
    public void onSaveButton(ActionEvent actionEvent) {

        int id = Integer.parseInt(partIdField.getText());
        String name = partNameField.getText();
        int inventory = Integer.parseInt(partInventoryField.getText());
        double price = Double.parseDouble(partPriceField.getText());
        int max = Integer.parseInt(partMaxField.getText());
        int min = Integer.parseInt(partMinField.getText());
    }

    //Cancel Button
    public void onCancelButton(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    //In House Radio Button
    public void onInHouseRadioButton(ActionEvent actionEvent) {
        System.out.println("I am the In House Button!!");
    }

    //Outsourced Radio Button
    public void onOutsourcedRadioButton(ActionEvent actionEvent) {
        System.out.println("I am the Outsourced Button!!");
    }

    /**
     * Initializes the controller function and populates the Table View
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }
}
