import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/** class that implements the Model of Bufe application */
public class BufeModel extends GridWorldModel {

    // constants for the grid objects
    public static final int STORAGE = 16;
    public static final int SUPPLIER = 24;
    public static final int COSTUMER  = 32;

    // the grid size
    public static final int GridSize = 9;

    // boolean carryingBeer = false; // whether the bufe is carrying beer
    int consumeCount = 0; // how much did the costumer eat/drink
    int availableProduct  = 16; // how many products are available

    Location lStorage = new Location(0, 0);
    Location lSupplier = new Location(0, GridSize - 1);
    Location lCostumer  = new Location(GridSize - 1, GridSize - 1);

    public BufeModel() {
        // create a 9x9 grid with one mobile agent
        super(GridSize, GridSize, 1);

        // initial location of bufe (column 4, line 4)
        // ag code 0 means the bufe
        setAgPos(0, GridSize/2, GridSize/2);

        // initial location of storage, costumer, supplier
        add(STORAGE, lStorage);
        add(SUPPLIER, lSupplier);
        add(COSTUMER, lCostumer);
    }

    boolean move(Location dest) {
        Location pos = getAgPos(0);
        if (pos.x < dest.x)
            pos.x++;
        else if (pos.x > dest.x)
            pos.x--;

        if (pos.y < dest.y)
            pos.y++;
        else if (pos.y > dest.y)
            pos.y--;
        setAgPos(0, pos); // move the bufe in the grid

        // repaint the storage, costumer, supplier locations
        if (view != null) {
            view.update(lStorage.x,lStorage.y);
            view.update(lCostumer.x,lCostumer.y);
            view.update(lSupplier.x,lSupplier.y);
        }
        return true;
    }

    boolean consumeProduct() {
        if (consumeCount > 0) {
            consumeCount--;
            if (view != null)
                view.update(lCostumer.x,lCostumer.y);
            return true;
        } else {
            return false;
        }
    }
}
