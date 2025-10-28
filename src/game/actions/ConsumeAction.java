package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.edibles.EdibleItem;

/**
 * Represents an action for an actor to consume an edible item in the game.
 * <p>
 * This class encapsulates the logic for consuming an item, such as food or drink,
 * that provides benefits or effects to the actor.
 * The consumption behavior is defined by the {@link EdibleItem#consume(Actor)} method.
 * </p>
 *
 * @see EdibleItem
 * @see Action
 */
public class ConsumeAction extends Action {

    /**
     * The edible item that will be consumed.
     */
    private EdibleItem item;

    /**
     * Constructor for creating a ConsumeAction.
     *
     * @param item the edible item to be consumed
     */
    public ConsumeAction(EdibleItem item) {
        this.item = item;
    }

    /**
     * Performs the consume action.
     * <p>
     * This method calls the {@code consume()} method on the {@link EdibleItem},
     * which handles the effects of the consumption on the actor.
     * </p>
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        return item.consume(actor);
    }

    /**
     * Provides a description of the action to be displayed in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string describing the action that will be shown in the menu.
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + item;
    }
}
