import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class BufeEnv extends Environment {

    @Override
    public void init(String[] args) {
        // initial percepts

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
