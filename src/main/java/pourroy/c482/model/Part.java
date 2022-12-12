package pourroy.c482.model;

/**
 * Serves as a Model of the Parts in the inventory
 *
 * @author Matthew Pourroy
 */
public abstract class Part {
    /**
     * Part Id
     * */
    private int id;

    /**
     * Part Name
     * */
    private String name;

    /**
     * Part Price
     * */
    private double price;

    /**
     * Inventory level
     * */
    private int stock;

    /**
     * Minimum Part inventory level required
     * */
    private int min;

    /**
     * Maximum Part inventory level required
     * */
    private int max;

    /**
     * Constructor for the Part object
     *
     * @param id is the Part ID
     * @param name is the Part Name
     * @param price is the Part Price
     * @param stock is the Inventory level
     * @param min is the Minimum Part inventory level required
     * @param max is the Maximum Part inventory level allowed
     * */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

}
