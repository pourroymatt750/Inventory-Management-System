package pourroy.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pourroy.c482.model.InHouse;
import pourroy.c482.model.Inventory;
import pourroy.c482.model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static pourroy.c482.model.Inventory.getNewPartId;

/**
 * Controller for the Add Part Page that provides functionality for the application
 *
 * @author Matthew Pourroy
 * */
public class AddPartController implements Initializable {
    /**
     * Machine ID/Company Name label
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
     * Text fields are validated with error messages that prevent the user from entering in correct data types
     * into text fields, checks to make sure Min is less than Max and that Stock is in between Min and Max
     *
     * @param actionEvent Save button action
     * @throws IOException from FXMLLoader
     * */
    @FXML
    void onSaveButton(ActionEvent actionEvent) throws IOException {

        try {
            int id = getNewPartId();
            String name = partNameField.getText();
            int stock = Integer.parseInt(partInventoryField.getText());
            double price = Double.parseDouble(partPriceField.getText());
            int max = Integer.parseInt(partMaxField.getText());
            int min = Integer.parseInt(partMinField.getText());

            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Minimum value must be less than maximum value");
                alert.showAndWait();
            } else if (stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Inventory level must be less than the maximum and greater than the minimum");
                alert.showAndWait();
            } else {
                if (inHouseRadioButton.isSelected()) {
                    int machineId = Integer.parseInt(partIdNameField.getText());
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
                }

                if (outsourcedRadioButton.isSelected()) {
                    String companyName = partIdNameField.getText();
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
                }

                Parent root = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 600);
                stage.setTitle("Home");
                stage.setScene(scene);
                stage.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please enter valid values in text fields");
            alert.showAndWait();
        }
    }

   /**
    * Shows Confirmation dialog and if user hits "OK" then it cancels any data user entered and returns to
    * main page
    *
    * @param actionEvent Cancel Button
    * @throws IOException from FXMLLoader
    * */
    public void onCancelButton(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("Are you sure you want to cancel? All data currently entered will be lost");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Sets the Machine ID/Company name label to "Machine ID"
     *
     * @param actionEvent In-House radio button
     * */
    public void onInHouseRadioButton(ActionEvent actionEvent) { partIdNameLabel.setText("Machine ID"); }

    /**
     * Sets the Machine ID/Company name label to "Company Name"
     * */
    public void onOutsourcedRadioButton(ActionEvent actionEvent) { partIdNameLabel.setText("Company Name"); }

    /**
     * Initializes the controller function and populates the Table View, sets the default radio button
     * to "Machine ID"/In-House radio button
     *
     * @param url is the URL used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle is the ResourceBundle used to localize the root object, or null if the root object was not localized.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partIdNameLabel.setText("Machine ID");
        inHouseRadioButton.setSelected(true);
    }
}
