package pourroy.c482;

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
 * Controller for the Home Page that provides functionality for the application
 *
 * @author Matthew Pourroy
 * */
public class HomePageController implements Initializable {
    /**
     * BEGIN Parts fields
     * */
    /**
     * Parts Search Bar field
     * */
    @FXML
    private TextField partSearchBar;

    /**
     * Table View for the Products table
     * Name label: "Products Table"
     * */
    @FXML
    private TableView<Part> partsTable;

    /**
     * Part ID for the Parts table
     * */
    @FXML
    private TableColumn<Part, Integer> partId;

    /**
     * Part Name for the Parts table
     * */
    @FXML
    private TableColumn<Part, String> partName;

    /**
     * Part Inventory Level for the Parts table
     * */
    @FXML
    private TableColumn<Part, Integer> partInventory;

    /**
     * Part Cost for the Parts table
     * */
    @FXML
    private TableColumn<Part, Double> partPrice;

    /**
     * Selected Part that user selects
     * */
    private static Part selectedPart;

    /**
     * Sets the part that the user clicks on as the Selected Part
     *
     * @param selected Selected Part
     * */
    public static void setSelectedPart(Part selected) {
        selectedPart = selected;
    }

    /**
     * Gets the part that the user clicks on as the Selected Part
     *
     * @return selectedPart
     * */
    public static Part getSelectedPart() {
        return selectedPart;
    }
    /**
     * END Parts fields
     * */



    /**
     * BEGIN Products fields
     * */
    /**
     * Products Search Bar field
     * */
    @FXML
    private TextField productSearchBar;

    /**
     * Table View for the Products table
     * Name label: "Products Table"
     * */
    @FXML
    private TableView<Product> productsTable;

    /**
     * Product ID column for the Products table
     * */
    @FXML
    private TableColumn<Product, Integer> productId;

    /**
     * Product Name column for the Products table
     * */
    @FXML
    private TableColumn<Product, String> productName;

    /**
     * Product Inventory Level column for the Products table
     * */
    @FXML
    private TableColumn<Product, Integer> productInventory;

    /**
     * Product Cost column for the Products table
     * */
    @FXML
    private TableColumn<Product, Double> productPrice;

    /**
     * Selected Product that user selects
     * */
    private static Product selectedProduct;

    /**
     * Sets the part that the user clicks on as the Selected Part
     *
     * @param selected Selected Part
     * */
    public static void setSelectedProduct(Product selected) {
        selectedProduct = selected;
    }

    /**
     * Gets the part that the user clicks on as the Selected Part
     *
     * @return selectedProduct Selected Product
     * */
    public static Product getSelectedProduct() {
        return selectedProduct;
    }
    /**
     * END Products fields
     * */


    /**
     * Parts Search Bar controller
     *
     * Can search for Parts based on ID or Name
     *
     * @param keyEvent Part search button
     * */
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
     * Takes user to the "Add Part" scene
     *
     * @param actionEvent  Part Add button action
     * @throws IOException From FXMLLoader
     * */
    public void onAddPart(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("add-part.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Allows user to modify a Part
     * Displays an error message if no Part is selected
     *
     * @param actionEvent Part modify button
     * @throws IOException from FXMLLoader
     * */
    public void onModifyPart(ActionEvent actionEvent) throws IOException {
        Part selected = partsTable.getSelectionModel().getSelectedItem();
        HomePageController.setSelectedPart(selected);

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No part selected");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("modify-part.fxml"));
                loader.load();

                ModifyPartController modifyPartController = loader.getController();
                modifyPartController.sendPart(partsTable.getSelectionModel().getSelectedItem(), partsTable.getSelectionModel().getSelectedIndex());

                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Parent root = loader.getRoot();
                Scene scene = new Scene(root, 800, 600);
                stage.setTitle("Modify Part");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes the part the user selected in the Part Table
     *
     * Displays error message if no Part is selected
     *
     * @param actionEvent Part delete button
     * */
    public void onDeletePart(ActionEvent actionEvent) {
        Part partSelected = partsTable.getSelectionModel().getSelectedItem();

        if (partSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No part selected");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
                Inventory.deletePart(partSelected);
        }
    }
    /***
     * END Parts Controller Functions
     */



    /**
     * BEGIN Product Controller Functions
     */
    /**
     * Parts Search Bar controller
     *
     * Can search for Parts based on ID or Name
     *
     * @param keyEvent Part search button
     * */
    public void onProductSearch(KeyEvent keyEvent) {

        String productName = productSearchBar.getText();

        ObservableList<Product> products = lookupProduct(productName);

        if (products.size() == 0) {
            try {
                int productID = Integer.parseInt(productName);
                Product product = lookupProduct(productID);
                if (product != null)
                    products.add(product);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Products Table is empty");
                alert.showAndWait();
            }
        }
        productsTable.setItems(products);
    }

    /**
     * Takes user to the "Add Product" scene
     *
     * @param actionEvent  Product Add button action
     * @throws IOException From FXMLLoader
     * */
    public void onAddProduct(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("add-product.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Allows user to modify a Product
     * Displays an error message if no Product is selected
     *
     * @param actionEvent Produt modify button
     * @throws IOException from FXMLLoader
     * */
    public void onModifyProduct(ActionEvent actionEvent) throws IOException {

        Product selected = productsTable.getSelectionModel().getSelectedItem();
        HomePageController.setSelectedProduct(selected);

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No part selected");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("modify-product.fxml"));
                loader.load();

                ModifyProductController modifyProductController = loader.getController();
                modifyProductController.sendProduct(productsTable.getSelectionModel().getSelectedItem());

                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Parent root = loader.getRoot();
                Scene scene = new Scene(root, 1000, 600);
                stage.setTitle("Modify Product");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Displays error message if no product is selected
     *
     * Checks to see if Product contains any Associated Parts. If it does, error message displays saying
     * Products with Associated Parts can NOT be deleted without deleting Associated Parts FIRST. If it
     * doesn't, it deletes the Product out of inventory
     *
     * @param actionEvent Delete button
     * */
    public void onDeleteProduct(ActionEvent actionEvent) {

        Product productSelected = productsTable.getSelectionModel().getSelectedItem();

        if (productSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No product selected");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (productSelected.getAllAssociatedParts().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
                Inventory.deleteProduct(productSelected);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No product with any associated parts may be deleted. " +
                    "Please delete associated parts first if product must be deleted");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    /**
     * END Product Controller Functions
     * */


    /**
     * Exits the application
     *
     * @param actionEvent Exit button
     * */
    public void onExitButton(ActionEvent actionEvent) { System.exit(0); }

    /**
     * Initializes the controller function and populates the Table View
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initializes and populates data in the Part Table
        partsTable.setItems(Inventory.getAllParts());
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Initializes and populates data in the Product Table
        productsTable.setItems((Inventory.getAllProducts()));
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}