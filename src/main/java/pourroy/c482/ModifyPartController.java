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
import pourroy.c482.model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static pourroy.c482.model.Inventory.getNewPartId;

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

    /**
     * Part that user selected to modify
     * */
    private Part selectedPart;

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

    public void inHouseRadioButtonAction(ActionEvent actionEvent) {
        partIdNameLabel.setText("Machine ID");
    }

    public void outsourcedRadioButtonAction(ActionEvent actionEvent) {
        partIdNameLabel.setText("Company Name");
    }

    public void saveButtonAction(ActionEvent actionEvent) {

        try {
            int id = getNewPartId();
            String name = partNameField.getText();
            int stock = Integer.parseInt(partInventoryField.getText());
            double price = Double.parseDouble(partPriceField.getText());
            int max = Integer.parseInt(partMaxField.getText());
            int min = Integer.parseInt(partMinField.getText());

            if (inHouseRadioButton.isSelected()) {
                int machineId = Integer.parseInt(partIdNameField.getText());
//                InHouse selectedPart = new InHouse(id, name, price, stock, min, max, machineId);
//                Inventory.updatePart();
                Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
            }

            if (outsourcedRadioButton.isSelected()) {
                String companyName = partIdNameField.getText();
                Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
            }

            Parent root = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid values in text fields");
            System.out.println("Exception: " + e);
            System.out.println("Exeption: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPart(Part part) {

        partIdField.setText(String.valueOf(part.getId()));
        partNameField.setText(part.getName());
        partInventoryField.setText(String.valueOf(part.getStock()));
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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
