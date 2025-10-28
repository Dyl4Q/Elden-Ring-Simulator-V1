package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.entities.FurnaceGolem;
import game.actors.entities.Player;
import game.terrain.Dirt;
import game.terrain.Floor;
import game.terrain.Puddle;
import game.terrain.Wall;
import game.items.weaponry.GreatKnife;
import game.items.weaponry.ShortSword;
import game.items.edibles.FlaskOfHealing;
import game.items.edibles.FlaskOfRejuvenation;
import game.items.edibles.ShadowTreeFragment;
import game.utilities.FancyMessage;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle());

        List<String> map = Arrays.asList(
                "..........~~~~~~~...~~~~~~~......~...........",
                "~..........~~~~~....~~~~~~...................",
                "~~.........~~~~.....~~~~~~...................",
                "~~~..#####..~~.....~~~~~~~...................",
                "~~~..#___#........~~~~~~~~~..................",
                "~~~..#___#.......~~~~~~.~~~..................",
                "~~~..##_##......~~~~~~.......................",
                "~~~~...........~~~~~~~...........~~..........",
                "~~~~~.........~~~~~~~~.......~~~~~~~.........",
                "~~~~~~.......~~~~~~~~~~.....~~~~~~~~.........");

        GameMap gameMap = new GameMap("Gravesite Plain", groundFactory, map);
        world.addGameMap(gameMap);

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Tarnished", '@', 150,  100, 5);
        world.addPlayer(player, gameMap.at(7, 4));

        gameMap.at(42, 4).addActor(new FurnaceGolem());

        gameMap.at(7, 8).addItem(new GreatKnife());
        gameMap.at(9, 8).addItem(new ShortSword());

        gameMap.at(8, 5).addItem(new FlaskOfRejuvenation());
        gameMap.at(6, 5).addItem(new FlaskOfHealing());

        gameMap.at(11, 4).addItem(new ShadowTreeFragment());
        gameMap.at(42, 5).addItem(new ShadowTreeFragment());
        gameMap.at(30, 9).addItem(new ShadowTreeFragment());
        gameMap.at(0, 0).addItem(new ShadowTreeFragment());
        gameMap.at(1, 4).addItem(new ShadowTreeFragment());

        world.run();

        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
