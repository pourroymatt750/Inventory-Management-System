package pourroy.c482.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Serves as a pourroy.c482.model of the Inventory which consists of Products and Parts
 *
 * @author Matthew Pourroy
 * */
public class Inventory {
    /**
     * List of all Parts in Inventory
     * */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * List of all Products in Inventory
     * */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     *Add a Part to Inventory
     *
     * @param newPart is added to Part object
     * */
    public static void addPart(Part newPart) { allParts.add(newPart); }

    /**
     *Add a Product to Inventory
     *
     * @param newProduct is added to Product object
     * */
    public static void addProduct(Product newProduct) { allProducts.add(newProduct); }

//    public static Part lookupPart(int partId) {}
//
//    public static Product lookupProduct(int productID) {}
//
//    public static ObservableList<Part> lookupPart(String partName) {}
//
//    public static ObservableList<Product> lookupProduct(String productName) {}
//
//    public static void updatePart(Part selectedPart, int index) {}
//
//    public static void updateProduct(Product newProduct, int index) {}
//
//    public static boolean deletePart(Part selectedPart) {}
//
//    public static boolean deleteProduct(Product selectedProduct) {}

    /**
     * Shows a list of all the Parts in Inventory
     *
     * @return List of Parts objects
     * */
    public static ObservableList<Part> getAllParts() { return allParts; }

    /**
     * Shows a list of all the Products in Inventory
     *
     * @return List of Products objects
     * */
    public static ObservableList<Product> getAllProducts() { return allProducts; }
}
