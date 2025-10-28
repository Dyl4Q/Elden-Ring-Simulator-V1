package game.terrain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utilities.Ability;
import game.utilities.Status;

/**
 * A class that represents the floor inside a building.
 * <p>
 * This class extends the {@link Ground} class to create a specific type of ground that
 * represents the floor of a building. The floor is capable of being burned and can be
 * walked on by actors that have the appropriate capability.
 * </p>
 */
public class Floor extends Ground {

    /**
     * Constructor for the Floor class.
     * <p>
     * Initializes the Floor with a display character and name. It also adds the
     * {@link Status#IS_BURNING} capability to indicate that this terrain can be affected by burning.
     * </p>
     */

    public Floor() {
        super('_', "Floor");
        this.addCapability(Status.IS_BURNING);
    }

    /**
     * Determines if an actor can enter this terrain.
     * <p>
     * This method checks if the actor has the {@link Ability#CAN_WALK_ON_FLOOR} capability.
     * Only actors with this capability are allowed to enter the Floor terrain.
     * </p>
     *
     * @param actor The actor attempting to enter the terrain.
     * @return true if the actor has the capability to walk on the floor, false otherwise.
     */

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Ability.CAN_WALK_ON_FLOOR);
    }
}
