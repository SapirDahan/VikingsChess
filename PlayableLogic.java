/**
 * The PlayableLogic interface defines the contract for the game logic
 * of chess-like games that can be played through a graphical user interface.
 * Implementing classes should provide the necessary logic to control the game,
 * manage player turns, and check for game completion.
 * DON'T MAKE ANY CHANGES HERE!
 */
public interface PlayableLogic {

    /**
     * Attempt to move a piece from one position to another on the game board.
     *
     * @param a The starting position of the piece.
     * @param b The destination position for the piece.
     * @return true if the move is valid and successful, false otherwise.
     */
    boolean move(Position a, Position b);

    /**
     * Get the piece located at a given position on the game board.
     *
     * @param position The position for which to retrieve the piece.
     * @return The piece at the specified position, or null if no piece is present.
     */
    Piece getPieceAtPosition(Position position);

    /**
     * Get the first player.
     *
     * @return The first player.
     */
    Player getFirstPlayer();

    /**
     * Get the second player.
     *
     * @return The second player.
     */
    Player getSecondPlayer();

    /**
     * Check if the game has finished, indicating whether a player has won or if it's a draw.
     *
     * @return true if the game has finished, false otherwise.
     */
    boolean isGameFinished();

    /**
     * Check if it is currently the second player's turn.
     *
     * @return true if it's the second player's turn, false if it's the first player's turn.
     */
    boolean isSecondPlayerTurn();

    /**
     * Reset the game to its initial state, clearing the board and player information.
     */
    void reset();

    /**
     * Undo the last move made in the game, reverting the board state and turn order.
     */
    void undoLastMove();

    /**
     * Get the size of the game board.
     *
     * @return The size of the game board, typically as the number of rows or columns.
     */
    int getBoardSize();
}
