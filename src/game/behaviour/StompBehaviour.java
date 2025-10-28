package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

/**
 * A class that represents a behaviour where an actor stomps on and attacks a target actor if they are adjacent.
 * <p>
 * This behaviour checks the exits around the actor to determine if the target actor is nearby. If the target is found,
 * the behaviour returns an {@link AttackAction} to stomp the target. If the target is not adjacent, no action is taken.
 * </p>
 *
 * @see edu.monash.fit2099.demo.mars.behaviours.SpitBehaviour
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */

public class StompBehaviour extends Action implements Behaviour {

    /**
     * The target actor that the behaviour is intended to attack.
     * This is the actor that the current actor will attempt to stomp if adjacent.
     */

    private final Actor target;

    /**
     * Constructor.
     *
     * @param subject the Actor to attack
     *                This is the target actor that the current actor will follow to stomp.
     */

    public StompBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Executes the action when selected. In this case, the method is not used since the behaviour determines
     * the action based on proximity to the target.
     * <p>
     * This method returns an empty string as it does not execute any actions directly. It is intended to be used
     * by the behaviour system to identify if an action should be performed based on target proximity.
     * </p>
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return an empty string, as this method does not execute any actions directly.
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        return "";
    }

    /**
     * Determines the action that allows the actor to attack the target if the target is adjacent.
     * <p>
     * The method checks all the adjacent locations (exits) of the actor. If the target is found in any of these locations,
     * it returns an {@link AttackAction} to stomp the target. Otherwise, it returns null.
     * </p>
     *
     * @param actor The actor performing the action.
     * @param map The game map containing the actor and the target.
     * @return an {@link AttackAction} if the target is adjacent, or null if the target is not nearby.
     */

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Check if both the target and the actor are present on the map
        if (!map.contains(target) || !map.contains(actor)) return null;

        // Get the current location of the actor
        Location here = map.locationOf(actor);
        Action getValue = null;

        // Iterate through all adjacent locations (exits) of the actor
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            Actor otherActor = map.getActorAt(destination);

            // If the adjacent location contains the target actor, prepare an AttackAction
            if (map.contains(otherActor) && otherActor.equals(target)) {
                getValue = new AttackAction(target, "");
            }
        }

        // Return the prepared AttackAction or null if the target is not adjacent
        return getValue;
    }

    /**
     * Provides a description of the action in the menu.
     * <p>
     * This method is not used for display purposes and returns an empty string.
     * </p>
     *
     * @param actor The actor performing the action.
     * @return an empty string, as this method is not used for display purposes.
     */

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}
