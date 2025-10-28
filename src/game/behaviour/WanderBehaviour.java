package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * A behaviour that causes an actor to wander to a random adjacent location.
 * <p>
 * This behaviour allows an actor to move to a random location adjacent to its current position.
 * If there are no valid moves available, the actor will remain in its current location.
 * </p>
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class WanderBehaviour implements Behaviour {

    /**
     * Random number generator used to select a random move action.
     */

    private final Random random = new Random();

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     * <p>
     * This method collects all possible MoveActions for adjacent locations where the actor can move.
     * It then randomly selects one of these actions to return. If no valid actions are available,
     * it returns null, meaning the actor will not move.
     * </p>
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action that moves the actor to a random valid adjacent location, or null if no valid moves exist
     */

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // List to store possible move actions
        ArrayList<Action> actions = new ArrayList<>();

        // Iterate through all exits (adjacent locations) from the actor's current location
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            // Check if the actor can enter the destination location
            if (destination.canActorEnter(actor)) {
                // Add a move action for the destination location to the actions list
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        // If there are valid actions, return a random one
        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        } else {
            // Return null if no valid move actions are available
            return null;
        }
    }
}
