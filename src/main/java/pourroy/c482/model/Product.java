package pourroy.c482.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Serves as a Model of the Products in the inventory
 *
 * @author Matthew Pourroy
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Product ID
     * */
    private int id;

    /**
     * Product Name
     * */
    private String name;

    /**
     * Product Price
     * */
    private double price;

    /**
     * Product Inventory level
     * */
    private int stock;

    /**
     * Minimum Product inventory level required
     * */
    private int min;

    /**
     * Maximum Product inventory level required
     * */
    private int max;

    /**
     * Constructor for the Product object
     *
     * @param id is Product ID
     * @param name is Product Name
     * @param price is Product Price
     * @param stock is Product Inventory level
     * @param min is Minimum Product inventory level required
     * @param max is Maximum Product inventory level allowed
     * */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Gets Product ID
     * */
    public int getId() {
        return id;
    }

    /**
     * Sets Product ID
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets Product Name
     * */
    public String getName() {
        return name;
    }

    /**
     * Sets Product Name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets Product Price
     * */
    public double getPrice() {
        return price;
    }

    /**
     * Sets Product Price
     * */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets Product Inventory level
     * */
    public int getStock() {
        return stock;
    }

    /**
     * Sets Product Inventory level
     * */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets Product Minimum inventory required
     * */
    public int getMin() {
        return min;
    }

    /**
     * Sets Product Minimum inventory required
     * */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets Product Maximum inventory allowed
     * */
    public int getMax() {
        return max;
    }

    /**
     * Sets Product Maximum inventory allowed
     * */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds associated part to selected product
     *
     * @param part is Part from Part Table that is added as an Associated Part
     * */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Removes associated part to selected product
     *
     * @param selectedAssociatedPart is Part from Part Table that is removed as an Associated Part
     * */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        return false;
    }

    /**
     * Gets all Associated parts
     *
     * @return A list of all Associated Parts
     * */
    public ObservableList<Part> getAllAssociatedParts() { return associatedParts; }
}
