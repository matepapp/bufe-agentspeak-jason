import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.Location;
import java.util.logging.Logger;

public class BufeEnv extends Environment {
    // common literals
    public static final Literal getProduct  = Literal.parseLiteral("get(Product)");
    public static final Literal movePosition  = Literal.parseLiteral("move(P)");
    public static final Literal giveProduct  = Literal.parseLiteral("give_prod(Product)");

    static Logger logger = Logger.getLogger(BufeEnv.class.getName());

    BufeModel model;

    @Override
    public void init(String[] args) {
        model = new BufeModel();

        if (args.length == 1 && args[0].equals("GUI")) {
            BufeView view  = new BufeView(model);
            model.setView(view);
        }

        updatePercepts();
    }

    void updatePercepts() {
        // clear the percepts of the agents
        clearPercepts("bufe");
        clearPercepts("costumer");
        clearPercepts("suppliers");
    }

    /**
     * Implementation of the agent's basic actions
     */
    @Override
    public boolean executeAction(String ag, Structure action) {
        System.out.println("Agent "+ag+" is doing "+action);
        boolean result = false;

        if (action.equals(giveProduct)) {
            result = model.giveProduct();
        } else if (action.equals(getProduct)) {
            result = model.getProduct();
        } else if (action.getFunctor().equals("move")) {
            String location = action.getTerm(0).toString();
            Location dest = null;
            if (location.equals("storage")) {
                dest = model.lStorage;
            } else if (location.equals("costumer")) {
                dest = model.lCostumer;
            }

            try {
                result = model.move(dest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.getFunctor().equals("deliver")) {
            // wait 5 seconds to finish "deliver"
            try {
                Thread.sleep(5000);
                result = model.addProduct( (int)((NumberTerm)action.getTerm(1)).solve());
            } catch (Exception e) {
                logger.info("Failed to execute action deliver!"+e);
            }
        }

        return result;
    }
}
