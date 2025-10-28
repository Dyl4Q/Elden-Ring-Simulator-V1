package game.terrain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utilities.Status;

/**
 * A class representing a wall that cannot be entered by any actor.
 * <p>
 * This class extends the {@link Ground} class to create a specific type of ground
 * representing an impassable wall. The wall is visually represented by the character '#'
 * and labeled as "Wall".
 * </p>
 */

public class Wall extends Ground {

    /**
     * Constructor for the Wall class.
     * <p>
     * Initializes the Wall with a display character and name. The display character
     * is '#', which is commonly used to represent solid barriers or walls in games.
     * </p>
     */

    public Wall() {
        super('#', "Wall");
        this.addCapability(Status.IS_BURNING);
    }

    /**
     * Determines whether an actor can enter this ground.
     * <p>
     * In this case, the wall is impassable, so this method always returns false,
     * indicating that no actor can enter or move onto the wall.
     * </p>
     *
     * @param actor The actor attempting to enter the ground.
     * @return false, as actors cannot enter a wall.
     */

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
