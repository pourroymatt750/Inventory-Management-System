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
import pourroy.c482.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static pourroy.c482.model.Inventory.getNewProductId;
import static pourroy.c482.model.Inventory.lookupPart;

/**
 * Controller for the Modify Product Page that provides functionality for the application
 *
 * @author Matthew Pourroy
 * */
public class ModifyProductController implements Initializable {
    /**
     * BEGIN Associated Part Table and Components
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

    public void saveButtonAction(ActionEvent actionEvent) {

        try {
            int id = getNewProductId();
            String name = productNameField.getText();
            int stock = Integer.parseInt(productStockField.getText());
            double price = Double.parseDouble(productPriceField.getText());
            int max = Integer.parseInt(productMaxField.getText());
            int min = Integer.parseInt(productMinField.getText());

            Product newProduct = new Product(id, name, price, stock, min, max);
            Inventory.addProduct(newProduct);

            Parent root = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 600);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid values in text fields");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeButtonAction(ActionEvent actionEvent) {
    }

    public void addButtonAction(ActionEvent actionEvent) {
    }

    public void sendProduct(Product product) {

        productIdField.setText(String.valueOf(product.getId()));
        productNameField.setText(product.getName());
        productStockField.setText(String.valueOf(product.getStock()));
        productPriceField.setText(String.valueOf(product.getPrice()));
        productMaxField.setText(String.valueOf(product.getMax()));
        productMinField.setText(String.valueOf(product.getMin()));
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
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }
}
