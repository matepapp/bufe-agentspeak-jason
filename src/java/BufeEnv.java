import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.Location;
import java.util.logging.Logger;

public class BufeEnv extends Environment {
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
        clearPercepts("janitor");
        clearPercepts("suppliers");
    }

    /**
     * Implementation of the agent's basic actions
     */
    @Override
    public boolean executeAction(String ag, Structure action) {
        System.out.println("Agent "+ag+" is doing "+action);
        boolean result = false;

        if (action.getFunctor().equals("deliver")) {
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
