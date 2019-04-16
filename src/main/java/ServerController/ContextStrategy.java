//Author: Federico Scatà
package ServerController;

import Model.Map;
import Model.Strategy;

public class ContextStrategy {
    private Strategy strategy;

    public ContextStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public Map doStrategy(){
        return strategy.initializeMap();
    }
}
