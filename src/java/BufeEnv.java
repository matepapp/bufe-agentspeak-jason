import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;
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
    public boolean executeAction(String ag, Structure act) {
        System.out.println("Agent "+ag+" is doing "+act);
        clearPercepts();

        return true;
    }
}
