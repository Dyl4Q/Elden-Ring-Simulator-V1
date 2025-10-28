package game.actors.entities;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.items.weaponry.BareFist;
import game.utilities.Status;
import game.actors.UserAttributes;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.utilities.Ability;

/**
 * Represents the player character in the game.
 * <p>
 * The Player class manages the player's attributes, including health, mana, and strength.
 * It also defines the player's capabilities, such as being hostile to enemies and being able to walk on the floor.
 * </p>
 * <p>
 * This class extends the {@link Actor} class and customizes the player-specific attributes and actions.
 * </p>
 *
 * @see Actor
 * @see BaseActorAttributes
 * @see Status
 * @see Ability
 */

public class Player extends Actor {

    /**
     * Constructor to create a new player with specified attributes.
     * <p>
     * Initializes the player's name, display character, hit points, mana, and strength.
     * It also sets up the player's capabilities and intrinsic weapon.
     * </p>
     *
     * @param name        The name of the player displayed in the UI.
     * @param displayChar The character used to represent the player on the map.
     * @param hitPoints   The initial hit points (health) of the player.
     * @param mana        The initial mana points of the player.
     * @param strength    The initial strength attribute of the player.
     */

    public Player(String name, char displayChar, int hitPoints, int mana, int strength) {
        super(name, displayChar, hitPoints);

        // Initialize player's mana and strength attributes
        this.addAttribute(BaseActorAttributes.MANA, new BaseActorAttribute(mana));
        this.addAttribute(UserAttributes.STRENGTH, new BaseActorAttribute(strength));

        // Add player capabilities
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.CAN_WALK_ON_FLOOR);

        // Set intrinsic weapon for the player
        this.setIntrinsicWeapon(new BareFist());
    }

    /**
     * Returns the name of the player.
     * <p>
     * This method provides the name of the player as a string.
     * </p>
     *
     * @return the name of the player.
     */

    public String getName() {
        return this.name;
    }

    /**
     * Manages the player's actions each turn.
     * <p>
     * This method handles multi-turn actions, displays the player's current stats, and
     * shows the menu of available actions for the player to choose from.
     * </p>
     *
     * @param actions    The list of possible actions for the player.
     * @param lastAction The action the player took last turn.
     * @param map        The map the player is on.
     * @param display    The display object used for showing messages.
     * @return the action that the player will perform on their turn.
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null) {
            return lastAction.getNextAction();
        }

        // Display the player's stats
        System.out.println(this.getName() + " (" + this.getAttribute(BaseActorAttributes.HEALTH) + "/"
                + this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ")");  // Current health and max health
        System.out.println("Mana: (" + this.getAttribute(BaseActorAttributes.MANA) + "/"
                + this.getAttributeMaximum(BaseActorAttributes.MANA) + ")");  // Current mana and max mana
        System.out.println("Strength: " + this.getAttribute(UserAttributes.STRENGTH));  // Current strength

        // Display the menu for the player's actions
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);  // Show the menu and return the selected action
    }
}
