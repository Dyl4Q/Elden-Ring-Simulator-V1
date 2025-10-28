package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Represents an action to attack another actor in the game.
 * <p>
 * This class encapsulates the logic for an actor attacking another actor
 * with a weapon. The weapon used can either be the actor's intrinsic weapon
 * (e.g., bare hands) or a weapon item the actor is holding.
 * <p>
 * Note that the attacker must have a weapon, otherwise the {@code execute()}
 * method will automatically assign the actor's intrinsic weapon.
 * <p>
 * This class also handles the consequences of the attack, such as
 * determining if the target actor becomes unconscious.
 * </p>
 *
 * @author Adrian Kristanto
 */
public class AttackAction extends Action {

    /**
     * The actor that is being attacked.
     */

    private Actor target;

    /**
     * The direction of the incoming attack, used for display purposes.
     */

    private String direction;

    /**
     * The weapon used for the attack. If null, the actor's intrinsic weapon will be used.
     */

    private Weapon weapon;

    /**
     * Constructor for creating an AttackAction with a specified weapon.
     *
     * @param target the actor to attack
     * @param direction the direction where the attack is performed (used for display purposes)
     * @param weapon the weapon used for the attack
     */

    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor for creating an AttackAction with the actor's intrinsic weapon.
     *
     * @param target the actor to attack
     * @param direction the direction where the attack is performed (used for display purposes)
     */

    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the attack action.
     * <p>
     * If no weapon is provided, the actor's intrinsic weapon is used.
     * This method also checks if the target actor becomes unconscious as a
     * result of the attack and handles that condition.
     * </p>
     *
     * @param actor the actor performing the attack
     * @param map the current game map
     * @return a description of the attack result
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        String result = weapon.attack(actor, target, map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * Returns a description of the action suitable for displaying in a menu.
     *
     * @param actor the actor performing the attack
     * @return a string describing the action
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}
