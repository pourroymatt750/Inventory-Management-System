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
import pourroy.c482.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static pourroy.c482.model.Inventory.lookupPart;

/**
 * Controller for the Modify Product Page that provides functionality for the application
 *
 * @author Matthew Pourroy
 * */
public class ModifyProductController implements Initializable {

    /**
     * Creates object that holds the stage and helps user change from scene to scene
     * */
    private Stage primaryStage;

    /**
     * Creates object that holds the scene and helps user change from scene to scene
     * */
    private Parent scene;

    /**
     * Holds reference to the product being selected
     * */
    Product product;

    /**
     * BEGIN Associated Part Table and Components
     * */
    /**
     * Associated Part Table
     * */
    @FXML
    private TableView<Part> assocPartTable;

    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

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
     * Associated Part Inventory Column
     * */
    @FXML
    private TableColumn<Part, Integer> assocPartStockCol;

    /**
     * Associated Part Price Column
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
     * Part Price Column
     * */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     *Search Bar for Parts
     * */
    @FXML
    private TextField partSearchBar;
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
     * Displays Confirmation Dialog asking user to confirm cancelling, then sends user back to
     * home page
     *
     * @param actionEvent Cancel button
     * @throws IOException from FXMLLoader
     * */
    public void cancelButtonAction(ActionEvent actionEvent) throws IOException {

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
     * Parts Search Bar controller
     *
     * Can search for Parts based on ID or Name
     *
     * @param keyEvent Part search button
     * */
    public void searchKeyPressed(KeyEvent keyEvent) {

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
     * Updates Part in inventory and sends user back to the Home Page
     *
     * Inputs validated to make sure right data type is entered
     *
     * Checks to make sure Min is less than Max, and that Stock is in between Min and Max
     * */
    public void saveButtonAction(ActionEvent actionEvent) throws IOException {

        try {
            int id = product.getId();
            String name = productNameField.getText();
            int stock = Integer.parseInt(productStockField.getText());
            double price = Double.parseDouble(productPriceField.getText());
            int max = Integer.parseInt(productMaxField.getText());
            int min = Integer.parseInt(productMinField.getText());

            if (min <= max) {
                if (stock >= min && stock <= max) {
                    product.setName(name);
                    product.setStock(stock);
                    product.setPrice(price);
                    product.setMax(max);
                    product.setMin(min);

//                    product.getAllAssociatedParts().addAll(assocParts);

                    primaryStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
                    primaryStage.setTitle("Home");
                    primaryStage.setScene(new Scene(scene));
                    primaryStage.show();

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
            alert.setContentText("Please enter valid values in text fields");
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
    public void removeButtonAction(ActionEvent actionEvent) {

        Part selectedPart = assocPartTable.getSelectionModel().getSelectedItem();

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
//                assocParts.remove(selectedPart);
//                assocPartTable.setItems(assocParts);
                assocPartTable.getItems().remove(selectedPart);
            }
        }
    }

    /**
     * Adds an Associated Part to the Associated Parts Table
     *
     * Displays error message if no Associated Part is selected
     * @param actionEvent Add button
     * */
    public void addButtonAction(ActionEvent actionEvent) {

        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {
//            assocParts.add(selectedPart);
//            assocPartTable.setItems(assocParts);
            assocPartTable.getItems().add(selectedPart);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No part selected");
            alert.showAndWait();
        }
    }

    /**
     * Sends data from the Save button to the Home Page
     *
     * */
    public void sendProduct(Product product) {

        this.product = product;
        productIdField.setText(String.valueOf(product.getId()));
        productNameField.setText(product.getName());
        productStockField.setText(String.valueOf(product.getStock()));
        productPriceField.setText(String.valueOf(product.getPrice()));
        productMaxField.setText(String.valueOf(product.getMax()));
        productMinField.setText(String.valueOf(product.getMin()));

        assocPartTable.setItems(product.getAllAssociatedParts());
        assocPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    /**
     * Initializes the controller function and populates the Table View
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        Product selectedProduct = HomePageController.getSelectedProduct();

        partsTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
