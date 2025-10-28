package game.terrain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Ground;
import game.utilities.Ability;
import game.utilities.Status;

/**
 * A class that represents a terrain on fire.
 * <p>
 * This class extends the {@link Ground} class to create a type of ground that simulates
 * a burning effect. The terrain will deal damage to any actor standing on it and will
 * revert to its original state after a certain number of turns.
 * </p>
 */

public class FlamingTerrain extends Ground {

    private final Ground originalGround; // The original ground that this terrain replaces
    private int burnRoundCounter = 5;     // Number of turns the fire will burn
    private int dmgFromBurning = 5;       // Amount of damage dealt to actors by the fire

    /**
     * Constructor for the FlamingTerrain class.
     * <p>
     * Initializes the FlamingTerrain with a display character and name.
     * It also records the original ground that it replaces and adds the
     * {@link Status#IS_BURNING} capability to indicate that this terrain is on fire.
     * </p>
     *
     * @param originalGround The ground that this terrain replaces once the fire burns out.
     */

    public FlamingTerrain(Ground originalGround) {
        super('w', "Fire");
        this.originalGround = originalGround;
        this.addCapability(Status.IS_BURNING);
    }

    /**
     * Method that is called every turn to update the terrain's state.
     * <p>
     * Decreases the burn round counter, damages any actor standing on the terrain,
     * and reverts to the original ground once the fire has burned for the specified number of turns.
     * </p>
     *
     * @param location The location of the terrain that is being updated.
     */

    @Override
    public void tick(Location location) {
        super.tick(location);
        burnRoundCounter--;

        // Apply damage to the actor standing on the terrain
        Actor actor = location.getActor();
        if (actor != null) {
            if (!(actor.hasCapability(Ability.FIRE_IMMUNE))) {
                actor.hurt(dmgFromBurning);
                System.out.println(actor + " takes " + dmgFromBurning + " fire damage!");
            }
        }

        // Revert to the original ground after the fire has burned out
        if (burnRoundCounter < 0) {
            location.setGround(originalGround);
        }
    }

    /**
     * Determines if an actor can enter this terrain.
     * <p>
     * This method is inherited from the {@link Ground} class and allows entry
     * based on the base implementation.
     * </p>
     *
     * @param actor The actor attempting to enter the terrain.
     * @return true if the actor can enter, false otherwise.
     */

    @Override
    public boolean canActorEnter(Actor actor) {
        return super.canActorEnter(actor);
    }
}
