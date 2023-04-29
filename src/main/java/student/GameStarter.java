package student;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class integrates the implementation of the student package and allows to play the text adventure scenarios.
 */
public class GameStarter {

    private static final String PROMPT = "play>";
    private static Player player = new Player();
    public Terminal terminal;
    private TextAdventure[] games;

    /**
     * Constructor to create a GameStarter instance.
     * @param games array with text adventures games to play
     */
    public GameStarter(TextAdventure[] games) {
        terminal = Factory.getTerminalF();
        this.games = games;
    }

    /**
     * Starts one of the given games.
     * @throws IOException 
     */
    public void startGame() throws IOException {
        String[] input;
        String prompt = "Play ( ";

        for (TextAdventure game : this.games) {
            prompt += game.getName();
            prompt += " | ";
        }
        prompt = prompt.substring(0, prompt.length() - 1) + " )";
        do {
            terminal.promptInput(prompt);
            input = terminal.readInput();
        } while (!prompt.contains(input[0].toString()));


        String gameName = input[0];
        for (TextAdventure game : this.games) {
            if (game.getName().equals(gameName)) {
                startGame(game, 0, 0);
        		System.out.println(game.getName());
                return;
            }
    		System.out.println(game.getName());

        }
        System.out.println("Unknown game scenario");
        System.exit(1);


    }

    /**
     * Starts a given game and runs a simple input-loop that accepts game-commands.
     * @param textAdventure to play
     * @param x coordinate of the player
     * @param y coordinate of the player
     * @throws IOException 
     */
    public void startGame(TextAdventure textAdventure, int x, int y) throws IOException {
        try {
            player = textAdventure.startGame(x, y);
        } catch (TextAdventureException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        boolean run = true;
        while (run) {
            try {
                terminal.promptInput(PROMPT);
                String[] input = terminal.readInput();
                run = processInput(input,textAdventure);
            } catch (TextAdventureException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Process the given command-line, translate it into player-actions if possible.
     * @param input the splitted user input
     * @return false if the user wants to quit, true otherwise
     * @throws TextAdventureException If an invalid command is entered
     */
    private static boolean processInput(String[] input,TextAdventure textAdventure) throws TextAdventureException {
        switch (input[0]) {
            case "go": {
                if (input.length < 2) {
                    System.out.println("Sorry, please specify a direction.");
                } else {
                    System.out.println(player.go(input[1],textAdventure));
                }
                return true;
            }
            case "look": {
               player.look(textAdventure.map);
                return true;
            }
            case "inventory": {
            	ArrayList<String> list = player.inventory();
                    System.out.println(list);
                
                return true;
            }
            case "take": {
                if (input.length < 2) {
                    System.out.println("Sorry, please specify an object.");
                } else {
                    System.out.println(player.take(input[1],textAdventure));
                }
                return true;
            }
            case "drop": {
                if (input.length < 2) {
                    System.out.println("Sorry, please specify an object.");
                } else {
                    System.out.println(player.drop(input[1],textAdventure));
                }
                return true;
            }
            case "convert": {
                if (input.length < 3) {
                    System.out.println("Sorry, please specify the objects to compose.");
                } else {
                    System.out.println(player.convert(input[1], input[2],textAdventure));
                }
                return true;
            }
            case "decompose": {
                if (input.length < 2) {
                    System.out.println("Sorry, please specify an object.");
                } else {
                    System.out.println(player.decompose(input[1],textAdventure));
                }
                return true;
            }
            case "help": {
                System.out.println("Valid Commands are: go, look, inventory, take, drop, compose, decompose");
                return true;
            }
            case "exit": {
                System.out.println("Bye!");
                return false;
            }
            default: {
                throw new TextAdventureException("Unknown Command: " + input[0]);
            }
        }
    }
}
