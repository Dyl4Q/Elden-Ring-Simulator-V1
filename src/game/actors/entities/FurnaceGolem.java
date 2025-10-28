package game.actors.entities;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import game.actions.AttackAction;
import game.utilities.Status;
import game.utilities.Ability;
import game.items.weaponry.BareStomp;
import game.behaviour.FollowBehaviour;
import game.behaviour.StompBehaviour;
import game.behaviour.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the Furnace Golem, a powerful entity in the game.
 * <p>
 * The Furnace Golem has a high health value and can engage in combat with hostile actors.
 * By default, it wanders around the map but can follow and attack enemies.
 * </p>
 * <p>
 * This class demonstrates a variety of behaviors, including wandering, following, and stomping.
 * The behaviours are stored in a map, prioritized by an integer key, where lower values represent
 * higher priority behaviors.
 * </p>
 *
 * @see Actor
 * @see Behaviour
 * @see WanderBehaviour
 * @see FollowBehaviour
 * @see StompBehaviour
 * @see AttackAction
 */

public class FurnaceGolem extends Actor {

    /**
     * A map of behaviors for the Furnace Golem, prioritized by integer keys.
     * Lower integer values indicate higher priority behaviors.
     */

    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor to create a Furnace Golem with high health and default behaviors.
     * <p>
     * The Furnace Golem starts with a wandering behavior and a bare stomp as its intrinsic weapon.
     * </p>
     */

    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000);  // Initialize Furnace Golem with high health
        this.behaviours.put(999, new WanderBehaviour());  // Default behavior is to wander
        this.addCapability(Ability.FIRE_IMMUNE);
        this.setIntrinsicWeapon(new BareStomp());  // Set BareStomp as the Golem's intrinsic weapon
    }

    /**
     * Determines the next action for the Furnace Golem during its turn.
     * <p>
     * The Golem checks its behaviors in priority order and performs the first available action.
     * If no action is available, it does nothing for that turn.
     * </p>
     *
     * @param actions      The list of possible actions.
     * @param lastAction   The action the actor took last turn.
     * @param map          The map the actor is on.
     * @param display      The display object used for showing messages.
     * @return the action that the Golem will perform on its turn.
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Iterate through the behaviors, prioritizing lower keys
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);  // Get the next action based on the behavior
            if (action != null)
                return action;  // Perform the first available action
        }
        return new DoNothingAction();  // If no action is available, do nothing
    }

    /**
     * Returns the allowable actions that other actors can perform on the Furnace Golem.
     * <p>
     * If an actor has the capability {@link Status#HOSTILE_TO_ENEMY}, they can attack the Golem.
     * In response, the Golem will begin following and stomping the hostile actor.
     * </p>
     *
     * @param otherActor The actor interacting with the Golem.
     * @param direction  The direction of the other actor relative to the Golem.
     * @param map        The map the Golem is on.
     * @return a list of actions the other actor can perform on the Golem.
     */

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();  // Initialize a list of allowable actions

        // Check if the other actor is hostile towards the Golem
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));  // Allow the other actor to attack the Golem
            // Update behaviors to follow and stomp the hostile actor
            this.behaviours.put(999, new FollowBehaviour(otherActor));  // Follow hostile actor with a lower priority
            this.behaviours.put(1, new StompBehaviour(otherActor));  // Stomp the hostile actor with a higher priority
        }

        return actions;  // Return the list of allowable actions
    }
}
