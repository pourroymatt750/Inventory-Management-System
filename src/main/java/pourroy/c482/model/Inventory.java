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

    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (int i=0; i < allParts.size(); i++) {
            Part part = allParts.get(i);

            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }


    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }

    public static Product lookupProduct(int productID) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (int i=0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);

            if (product.getId() == productID) {
                return product;
            }
        }

        return null;
    }
//
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    public static void updatePart(int index, Part selectedPart) {
        index = allParts.indexOf(selectedPart);
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        index = allProducts.indexOf(newProduct);
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initializes Part ID to 3 because that's the last ID number of the preloaded data
     * */
    private static int partId = 3;

    /**
     * Initializes Product ID to 3 because that's the last ID number of the preloaded data
     * */
    private static int productId = 3;

    /**
     * Auto-generates a new part ID upon every added part
     * */
    public static int getNewPartId() {
        return ++partId;
    }

    /**
     * Auto-generates a new product ID upon every added product
     * */
    public static int getNewProductId() {
        return ++productId;
    }

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
