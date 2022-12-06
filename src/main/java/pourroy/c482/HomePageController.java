package pourroy.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
     * END Products fields
     * */

    //BEGIN Parts Controller Functions
    //Parts Search Bar controller
    public void onPartSearch(KeyEvent keyEvent) {
        System.out.println("I am the Parts Search Field!!");
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

    //Modify Part Button
    public void onModifyPart(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("add-part.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    //Delete Part Button
    public void onDeletePart(ActionEvent actionEvent) {
        System.out.println("I am the Delete Part Button!!");
    }
    //END Parts Controller Functions

    //BEGIN Product Controller Functions
    //Product Search Bar
    public void onProductSearch(KeyEvent keyEvent) {
        System.out.println("I am the Product Search Field!!");
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

    //Modify Product Button
    public void onModifyProduct(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("modify-product.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    //Delete Product Button
    public void onDeleteProduct(ActionEvent actionEvent) {
        System.out.println("I am the Delete Product Button!!");
    }
    //END Product Controller Functions

    //Exit Button
    public void onExitButton(ActionEvent actionEvent) {
        System.out.println("I am the Exit Button!!");
    }

    /**
     * Initializes the controller function and populates the Table View
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initializes and populates data in the Part Table
        partsTable.setItems(Inventory.getAllParts());
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Initializes and populates data in the Product Table
        productsTable.setItems((Inventory.getAllProducts()));
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}