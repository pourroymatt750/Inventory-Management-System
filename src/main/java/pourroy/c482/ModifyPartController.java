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
import pourroy.c482.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Controller for the Modify Part Page that provides functionality for the application
 *
 * @author Matthew Pourroy
 * */
public class ModifyPartController implements Initializable {

    /**
     * Sets primaryStage as the stage object
     * */
    private Stage primaryStage;

    /**
     * Sets scene as the scene object
     * */
    private Parent scene;

    /**
     * Part user selected
     * */
    Part part;

    /**
     * Label for Machine ID/Company Name in the Parts Table View
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
    private TextField partStockField;

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
    private int index;

    /**
     * Displays Confirmation Dialog asking user to confirm cancelling, then sends user back to
     * home page
     *
     * @param actionEvent Cancel button
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
     * Sets the Machine ID/Company Name label to "Machine ID"
     *
     * @param actionEvent In-House radio button
     * */
    public void inHouseRadioButtonAction(ActionEvent actionEvent) { partIdNameLabel.setText("Machine ID"); }

    /**
     * Sets the Machine ID/Company Name label to "Company Name"
     *
     * @param actionEvent Outsourced radio button
     * */
    public void outsourcedRadioButtonAction(ActionEvent actionEvent) { partIdNameLabel.setText("Company Name"); }

    /**
     * Updates Part in inventory and sends user back to the Home Page
     *
     * Inputs validated to make sure right data type is entered
     *
     * Checks to make sure Min is less than Max, and that Stock is in between Min and Max
     * */
    public void saveButtonAction(ActionEvent actionEvent) throws IOException {

        try {
            int id = part.getId();
            String name = partNameField.getText();
            int stock = Integer.parseInt(partStockField.getText());
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
                    InHouse inHouse = new InHouse(id, name, price, stock, min, max, machineId);
                    Inventory.updatePart(index, inHouse);
                }

                if (outsourcedRadioButton.isSelected()) {
                    String companyName = partIdNameField.getText();
                    Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.updatePart(index, outsourced);
                }

                primaryStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
                primaryStage.setTitle("Home");
                primaryStage.setScene(new Scene(scene));
                primaryStage.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please enter valid values in text fields");
            alert.showAndWait();
        }
    }

    /**
     * Sends data from the Save button to the Home Page
     *
     * */
    public void sendPart(Part part, int index) {
        this.index = index;
        this.part = part;
        partIdField.setText(String.valueOf(part.getId()));
        partNameField.setText(part.getName());
        partStockField.setText(String.valueOf(part.getStock()));
        partPriceField.setText(String.valueOf(part.getPrice()));
        partMaxField.setText(String.valueOf(part.getMax()));
        partMinField.setText(String.valueOf(part.getMin()));

        if (part instanceof InHouse) {
            inHouseRadioButton.setSelected(true);
            partIdNameLabel.setText("Machine ID");
            partIdNameField.setText(String.valueOf(((InHouse) part).getMachineId()));
        }

        if (part instanceof Outsourced) {
            outsourcedRadioButton.setSelected(true);
            partIdNameLabel.setText("Company Name");
            partIdNameField.setText(String.valueOf(((Outsourced) part).getCompanyName()));
        }
    }

    /**
     * Initializes the controller function and populates the Table View
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
}
