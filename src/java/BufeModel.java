import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/** class that implements the Model of Bufe application */
public class BufeModel extends GridWorldModel {

    // constants for the grid objects
    public static final int STORAGE = 16;
    public static final int COSTUMER  = 32;

    // the grid size
    public static final int GridSize = 9;

    int consumeCount = 0; // how much did the costumer eat/drink
    int availableProduct  = 16; // how many products are available

    Location lStorage = new Location(0, 0);
    Location lCostumer  = new Location(GridSize - 1, GridSize - 1);

    public BufeModel() {
        // create a 9x9 grid with one mobile agent
        super(GridSize, GridSize, 2);

        // initial location of bufe (column 4, line 4)
        // ag code 0 means the bufe
        // ag code 1 means the supplier
        setAgPos(0, GridSize/2, GridSize/2);
        setAgPos(1, GridSize - 1, 0);

        // initial location of storage, costumer, supplier
        add(STORAGE, lStorage);
        add(COSTUMER, lCostumer);
    }

    boolean move(Location dest) {
        Location bufePos = getAgPos(0);
        if (bufePos.x < dest.x)
            bufePos.x++;
        else if (bufePos.x > dest.x)
            bufePos.x--;

        if (bufePos.y < dest.y)
            bufePos.y++;
        else if (bufePos.y > dest.y)
            bufePos.y--;
        setAgPos(0, bufePos); // move the bufe on the grid

        Location supPos = getAgPos(1);
        if (supPos.x < dest.x)
            supPos.x++;
        else if (supPos.x > dest.x)
            supPos.x--;
        setAgPos(1, supPos); // move the supplier on the grid

        // repaint the storage, costumer locations
        if (view != null) {
            view.update(lStorage.x,lStorage.y);
            view.update(lCostumer.x,lCostumer.y);
        }
        return true;
    }

    boolean addProduct(int n) {
        availableProduct += n;
        if (view != null)
            view.update(lStorage.x,lStorage.y);
        return true;
    }

    boolean payProduct() {
        if (view != null)
            view.update(lCostumer.x,lCostumer.y);
        return true;
    }

    boolean giveProduct() {
        consumeCount = 40;

        Location pos = getAgPos(0);
        pos.x--;
        pos.y--;
        setAgPos(0, pos); // move the bufe 1 back

        if(view != null)
            view.update(lCostumer.x, lCostumer.y);
        return true;
    }

    boolean getProduct() {
      availableProduct--;
      if (view != null)
          view.update(lStorage.x,lStorage.y);
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
