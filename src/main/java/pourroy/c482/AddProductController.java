package pourroy.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pourroy.c482.model.Inventory;
import pourroy.c482.model.Part;
import pourroy.c482.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static pourroy.c482.model.Inventory.*;

/**
 * Controller for the Add Product Page that provides functionality for the application
 *
 * @author Matthew Pourroy
 * */
public class AddProductController implements Initializable {
    /**
     *Search Bar for Parts
     * */
    @FXML
    private TextField partSearchBar;

    /**
     * BEGINS Associated Part Table and Components
     * */
    /**
     * Associated Part Table
     * */
    @FXML
    private TableView<Part> assocPartTable;

    /**
     * Associated Part ID Column
     * */
    @FXML
    private TableColumn<Part, Integer> assocPartIdCol;

    /**
     * Associated Part Name Column
     * */
    @FXML
    private TableColumn<Part, String> assocPartNameCol;

    /**
     * Associated Part Inventory Level Column
     * */
    @FXML
    private TableColumn<Part, Integer> assocPartStockCol;

    /**
     * Associated Part Cost per Unit Column
     * */
    @FXML
    private TableColumn<Part, Double> assocPartPriceCol;
    /**
     * END Associated Part Table and Components
     * */


    /**
     * BEGIN Part Table and Components
     * */
    /**
     * Part Table
     * */
    @FXML
    private TableView<Part> partsTable;

    /**
     * Part ID Column
     * */
    @FXML
    private TableColumn<Part, Integer> partIdCol;

    /**
     * Part Name Column
     * */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * Part Inventory Level Column
     * */
    @FXML
    private TableColumn<Part, Integer> partStockCol;

    /**
     * Part Cost per Unit Column
     * */
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    /**
     * END Part Table and Components
     * */

    /**
     * Product ID text field
     * */
    @FXML
    private TextField productIdField;

    /**
     * Product Name text field
     * */
    @FXML
    private TextField productNameField;

    /**
     * Product Inventory text field
     * */
    @FXML
    private TextField productStockField;

    /**
     * Product Price text field
     * */
    @FXML
    private TextField productPriceField;

    /**
     * Product Max inventory count text field
     * */
    @FXML
    private TextField productMaxField;

    /**
     * Product Minimum inventory count text field
     * */
    @FXML
    private TextField productMinField;

    /**
     * List of Associated Parts that are linked to the product the user is adding
     * */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    /**
     * Shows Confirmation Dialog and Cancel button takes user back to home page
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
     * Searches for a Part based on the Part ID or Part name
     *
     * Populates a list of parts that match what is typed into Search Bar once user hits "ENTER"
     *
     * @param keyEvent Part SearchBar
     * */
    //Search for a Part with this controller function
    public void onPartSearch(KeyEvent keyEvent) {

        String partName = partSearchBar.getText();

        ObservableList<Part> parts = lookupPart(partName);

        if (parts.size() == 0) {
            try {
                int partId = Integer.parseInt(partName);
                Part part = lookupPart(partId);
                if (part != null)
                    parts.add(part);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Parts Table is empty");
                alert.showAndWait();
            }
        }
        partsTable.setItems(parts);
    }

    /**
     * Adds new Product into inventory and takes user back to Home Page
     *
     * Text fields are validated with error messages that prevent the user from entering in correct data types
     * into text fields, checks to make sure Min is less than Max and that Stock is in between Min and Max
     *
     * @param actionEvent Save button action
     * @throws IOException from FXMLLoader
     * */
    //Save a Part in inventory
    public void onSaveButton(ActionEvent actionEvent) throws IOException {

        try {
            int id = getNewProductId();
            String name = productNameField.getText();
            int stock = Integer.parseInt(productStockField.getText());
            double price = Double.parseDouble(productPriceField.getText());
            int max = Integer.parseInt(productMaxField.getText());
            int min = Integer.parseInt(productMinField.getText());

            if (min <= max) {
                if (stock >= min && stock <= max) {
                    Product newProduct = new Product(id, name, price, stock, min, max);

                    newProduct.setId(Inventory.getNewProductId());
                    Inventory.addProduct(newProduct);

                    Parent root = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
                    Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1000, 600);
                    stage.setTitle("Home");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("Inventory level must be less than the maximum and greater than the minimum");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Minimum value must be less than maximum value");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please enter valid values");
            alert.showAndWait();
        }
    }

    /**
     * Displays Confirmation Dialog asking user if user wants to remove Associated part from the Product the user
     * is adding
     *
     * Displays error message if no Associated Part is selected
     *
     * @param actionEvent Remove button
     * */
    public void onRemoveButton(ActionEvent actionEvent) {

        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No part selected");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("Are you sure you want to remove associated part?");
           Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                assocParts.remove(selectedPart);
                assocPartTable.setItems(assocParts);
            }
        }
    }

    /**
     * Adds an Associated Part to the Associated Parts Table
     *
     * Displays error message if no Associated Part is selected
     * @param actionEvent Add button
     * */
    public void onAddButton(ActionEvent actionEvent) {

        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {
            assocParts.add(selectedPart);
            assocPartTable.setItems(assocParts);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No part selected");
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller function and populates the Table View
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
