package pourroy.c482;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    //Initialize Page
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }

    //BEGIN Parts Controller Functions
    //Parts Search Bar
    public void onPartSearch(ActionEvent actionEvent) {
        System.out.println("I am the Parts Search Field!!");
    }

    //Add Part Button
    public void onAddPart(ActionEvent actionEvent) {
        System.out.println("I am the Add Part Button!!");
    }

    //Modify Part Button
    public void onModifyPart(ActionEvent actionEvent) {
        System.out.println("I am the Modify Part Button!!");
    }

    //Delete Part Button
    public void onDeletePart(ActionEvent actionEvent) {
        System.out.println("I am the Delete Part Button!!");
    }
    //END Parts Controller Functions

    //BEGIN Product Controller Functions
    //Product Search Bar
    public void onProductSearch(ActionEvent actionEvent) {
        System.out.println("I am the Product Search Field!!");
    }

    //Add Product Button
    public void onAddProduct(ActionEvent actionEvent) {
        System.out.println("I am the Add Product Button!!");
    }

    //Modify Product Button
    public void onModifyProduct(ActionEvent actionEvent) {
        System.out.println("I am the Modify Product Button!!");
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
}