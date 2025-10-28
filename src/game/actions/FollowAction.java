package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents an action that moves an actor to follow a specific target in the game.
 * <p>
 * This class extends {@link MoveActorAction} and allows an actor to move
 * to a specific location associated with a target actor. The movement
 * can be used for pursuing a target actor, such as an enemy or ally.
 * </p>
 *
 * @see MoveActorAction
 */

public class FollowAction extends MoveActorAction {

    /**
     * The target actor that is being followed.
     */

    private String target;

    /**
     * Constructor to create a FollowAction that will move the actor to a specific location of a target.
     * <p>
     * This constructor does not verify if the supplied location is reachable from the actor's current location,
     * allowing for movement behaviors like teleportation or movement to special locations.
     * </p>
     *
     * @param moveToLocation the location to move to
     * @param target the target actor to follow, described as a string (e.g., "Player")
     */

    public FollowAction(Location moveToLocation, Actor target) {
        super(moveToLocation, target.toString());
        this.target = target.toString();
    }

    /**
     * Provides a description of the follow action to be displayed in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string describing the follow action, e.g., "Player is following Target"
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is following " + target;
    }
}
