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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pourroy.c482.model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private TableColumn<Part, Integer> assocPartInventoryCol;

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
    private TableView<Part> partTable;

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
    private TableColumn<Part, Integer> partInventoryCol;

    /**
     * Part Price Column
     * */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     *Search Bar for Parts
     * */
    @FXML
    private TextField partSearchText;
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
    private TextField productInventoryField;

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

        Parent root = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public void searchKeyPressed(KeyEvent keyEvent) {
    }

    public void saveButtonAction(ActionEvent actionEvent) {
    }

    public void removeButtonAction(ActionEvent actionEvent) {
    }

    public void addButtonAction(ActionEvent actionEvent) {
    }

    /**
     * Initializes the controller function and populates the Table View
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }
}
