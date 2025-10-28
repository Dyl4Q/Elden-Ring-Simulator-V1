package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FollowAction;

/**
 * A class that determines a {@link FollowAction} to move the actor one step closer to a target Actor.
 * <p>
 * This behaviour allows an Actor to follow another Actor by finding the closest step toward the target
 * based on the Manhattan distance. If no closer step is found or the target is not on the map, the Actor
 * will remain in place.
 * </p>
 *
 * @see edu.monash.fit2099.demo.mars.behaviours.FollowBehaviour
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */

public class FollowBehaviour implements Behaviour {

    /**
     * The target Actor to be followed.
     * This actor is the destination the current actor will attempt to move towards.
     */

    private final Actor target;

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     *                This is the target actor that the current actor will follow.
     */

    public FollowBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Determines the action that allows the actor to follow the target.
     * <p>
     * If the target is not present on the map, or if the actor cannot move closer,
     * the method returns null. The actor will attempt to move to the closest location
     * that reduces the distance to the target.
     * </p>
     *
     * @param actor The actor performing the action.
     * @param map The game map containing the actor and the target.
     * @return a {@link FollowAction} that moves the actor closer to the target, or null if no valid move exists.
     */

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Check if both the actor and target are on the map
        if (!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        // Calculate the current distance to the target
        double currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            // Check if the destination location can be entered by the actor
            if (destination.canActorEnter(actor)) {
                // Calculate the distance to the target from the destination
                double newDistance = distance(destination, there);
                // If moving to the destination reduces the distance to the target, return a FollowAction
                if (newDistance < currentDistance) {
                    return new FollowAction(destination, target);
                }
            }
        }

        // Return null if no valid move is found
        return null;
    }

    /**
     * Computes the Manhattan distance between two locations.
     * <p>
     * The Manhattan distance is the number of steps required to move from one location to another
     * if only moving in the four cardinal directions (north, south, east, west).
     * </p>
     *
     * @param a the first location
     * @param b the second location
     * @return the Manhattan distance between the two locations
     */

    private double distance(Location a, Location b) {
        return Math.sqrt(Math.pow(a.x() - b.x(), 2) + Math.pow(a.y() - b.y(), 2));
    }
}
