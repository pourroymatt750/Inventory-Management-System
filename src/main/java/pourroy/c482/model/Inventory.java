package pourroy.c482.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Serves as a Model of the Inventory which consists of Products and Parts
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

    /**
     * Searches list of parts in Parts Table by ID
     *
     * @param partId is the Part ID
     * @return Part object if found in list, null otherwise
     * */
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

    /**
     * Searches list of parts in Parts Table by Name
     *
     * @param partName is the Part Name
     * @return Part object if found in list, null otherwise
     * */
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

    /**
     * Searches list of products in Products Table by ID
     *
     * @param productID is the Product ID
     * @return Product object if found in list, null otherwise
     * */
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

    /**
     * Searches list of products in Products Table by Name
     *
     * @param productName is the Product Name
     * @return Product object if found in list, null otherwise
     * */
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

    /**
     * Updates a part
     *
     * @param id is the Product ID
     * @param selectedPart is the part selected by the user
     * */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Updates a product
     *
     * @param id is the Product ID
     * @param newProduct is the product selected by the user
     * */
    public static void updateProduct(int id, Product newProduct) {
        int index = allProducts.indexOf(newProduct);
        allProducts.set(index, newProduct);
    }

    /**
     * Deletes a part
     *
     * @param selectedPart is the part selected by the user
     * */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes a product
     *
     * @param selectedProduct is the product selected by the user
     * */
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
     *
     * @return a new part ID
     * */
    public static int getNewPartId() {
        return ++partId;
    }

    /**
     * Auto-generates a new product ID upon every added product
     *
     * @return a new product ID
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
