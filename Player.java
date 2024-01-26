/**
 * The Player interface defines the characteristics and behaviors of a player
 * in a chess-like game. Implementing classes should provide information about
 * which player is it and track the number of wins.
 */
public interface Player {

    /**
     *
     * @return true if the player is player 1, false otherwise.
     */
    boolean isPlayerOne();

    /**
     * Get the number of wins achieved by the player in the game.
     *
     * @return The total number of wins by the player.
     */
    int getWins();
}
