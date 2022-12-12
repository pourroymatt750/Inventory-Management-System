package pourroy.c482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pourroy.c482.model.*;

import java.io.IOException;

/**
 * Home Page that provides initial values for the application
 *
 * @author Matthew Pourroy
 * */
public class HomePageApplication extends Application {
    /**
     * The start method creates the FXML stage and loads the initial scene.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageApplication.class.getResource("home-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method is the entry point of the application.
     *
     * The main method creates sample data and launches the application.
     *
     * @param args
     */
    public static void main(String[] args) {

        //Sample Parts upon initialization
        InHouse inHouse1 = new InHouse(1, "Brakes", 12.99, 15, 1,20, 10);
        InHouse inHouse2 = new InHouse(2, "Tire", 14.99, 15, 1, 25, 11);
        Outsourced outsourced1 = new Outsourced(3,"Rim", 56.99, 15, 1,20, "Michelin");

        Inventory.addPart(inHouse1);
        Inventory.addPart(inHouse2);
        Inventory.addPart(outsourced1);


        //Sample Products upon initialization
        Product product1 = new Product(1, "Giant Bicycle", 299.99, 15, 1, 20);
        Product product2 = new Product(2, "Scott Bicycle", 199.99, 15, 1, 17);
        Product product3 = new Product(3, "GT Bike", 9.99, 15, 1, 16);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);

        launch();
    }
}