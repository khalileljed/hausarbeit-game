package student;

import java.io.Console;

/**
 * Factory class with functions to generate a game and terminal.
 */
public final class Factory {
    
    private Factory() { }
        
    /**
     * Creates a new game-instance with an empty board of the given size.
     * @param name The name of the game
     * @param boardWidth the width of the board
     * @param boardHeight the height of the board
     * @return a fresh game instance
     */
    public static ias.TextAdventure getGame(String name, int boardWidth, int boardHeight)
            throws ias.TextAdventureException {
        //placeholder
        return null;
    }
    public static TextAdventure getGameF(String name, int boardHeight, int boardWidth)
            throws TextAdventureException {
    	TextAdventure TA = new TextAdventure(name,boardHeight,boardWidth);
        return TA;
    }
    /**
     * Creates a new terminal-instance to read user input and prompt messages.
     * @return a terminal instance
     */ 
    public static ias.Terminal getTerminal() {
        //placeholder
        return null;
    }
    public static Terminal getTerminalF() {
    	Terminal terminal = new Terminal();
        return terminal;
    }
}
