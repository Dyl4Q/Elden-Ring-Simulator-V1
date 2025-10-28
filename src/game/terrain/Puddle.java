package game.terrain;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents a random puddle of water.
 * <p>
 * This class extends the {@link Ground} class to create a specific type of ground
 * representing a puddle of water. The puddle is visually represented by the character '~'
 * and labeled as "Puddle".
 * </p>
 */

public class Puddle extends Ground {

    /**
     * Constructor for the Puddle class.
     * <p>
     * Initializes the Puddle with a display character and name. The display character
     * is '~', which is commonly used to represent water or liquid in games.
     * </p>
     */

    public Puddle() {
        super('~', "Puddle");
    }
}
