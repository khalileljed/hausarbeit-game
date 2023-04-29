package student;
import java.io.*;

/**
 * Main class, serves as an entry point to the program.
 */
public class Main {

    /**
     * Main method which serves as an entry to the program.
     * @param args terminal parameters
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        try {
            // Add additional games to the Array
            TextAdventure[] games = {
                    Adventures.getFiremanGame(), Adventures.getLumberjackGame(), Adventures.getPokemonGame()
            };
            GameStarter starter = new GameStarter(games);
            starter.startGame();
        } catch (TextAdventureException e) {
            e.printStackTrace();
        }

    }
}
