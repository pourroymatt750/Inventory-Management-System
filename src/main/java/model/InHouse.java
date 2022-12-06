package model;

/**
 * Serves as a model of the In-House Parts in the Inventory
 *
 * @author Matthew Pourroy
 * */
public class InHouse extends Part {
    /**
     * Machine ID
     * */
    private int machineId;

    /**
     * Constructor for the instance of an In-House object and uses super to inherit from the Inventory class
     *
     * @@param id is the Part I.D.
     * @param  name is the Part Name
     * @param  price is the Part Cost
     * @param stock is the number of parts/products that's currently in inventory
     * @param min is the minimum number of parts/products that must be carried in inventory
     * @param max is the maximum number of parts/products that is allowed to be carried in inventory
     * @param machineId is the Machine ID of the part
     * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Gets the Machine ID
     *
     * @return machineId of the part
     * */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets the Machine ID
     *
     * @param machineId is the Machine ID of the part
     * */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
