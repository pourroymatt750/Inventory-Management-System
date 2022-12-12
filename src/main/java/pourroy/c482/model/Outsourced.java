package pourroy.c482.model;

/**
 * Serves as a Model of the Outsourced Parts in the Inventory
 *
 * @author Matthew Pourroy
 * */
public class Outsourced extends Part {
    /**
     * Company name
     * */
    private String companyName;

    /**
     * Constructor for the Outsourced part instance
     *
     * @param id is Part ID
     * @param name is Part Name
     * @param price is the Part Price
     * @param stock is the Inventory level
     * @param min is the Minimum level inventory required
     * @param max is the Maximum level inventory allowed
     * @param companyName is the Company name
     * */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Gets Company Name
     * */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the Company Name
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
