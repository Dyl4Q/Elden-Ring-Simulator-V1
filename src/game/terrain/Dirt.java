package game.terrain;

import edu.monash.fit2099.engine.positions.Ground;
import game.utilities.Status;

/**
 * A class representing bare dirt terrain.
 * <p>
 * This class extends the {@link Ground} class to create a specific type of ground,
 * which in this case is bare dirt. It also adds a capability to indicate that
 * this terrain can be set on fire.
 * </p>
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */

public class Dirt extends Ground {

    /**
     * Constructor for the Dirt class.
     * <p>
     * Initializes the Dirt object with a display character and a name.
     * It also adds the {@link Status#IS_BURNING} capability to the terrain.
     * </p>
     */

    public Dirt() {
        super('.', "Dirt");
        this.addCapability(Status.IS_BURNING);
    }
}
