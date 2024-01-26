import javax.swing.*;
import java.awt.*;

/**
 * IMPORTANT:
 * DON'T MAKE ANY CHANGES HERE
 */
public class GUI_for_chess_like_games extends JFrame {
    private static final int BUTTON_SIZE = 55;
    private static final int FONT_SIZE = 20;
    private final JButton[][] buttons;
    private final int BOARD_SIZE;
    private JButton selectedButton = null; // To keep track of the currently selected button
    private Color selectedColor = null;
    private final PlayableLogic gameLogic;
    private final JLabel turnLabel = new JLabel("Player 2's Turn");
    private final JLabel playerTowWinsLabel = new JLabel("♟ Player 2 Wins: 0");
    private final JLabel playerOneWinsLabel = new JLabel("♙ Player 1 Wins: 0");
    private final JPanel mainPanel = new JPanel(new BorderLayout());

    /**
     * Initializes the graphical user interface for the Vikings Chess Game.
     *
     * @param gameLogic The game logic instance that controls the game's logic and state.
     */
    public GUI_for_chess_like_games(PlayableLogic gameLogic, String title) {
        this.gameLogic = gameLogic;
        this.BOARD_SIZE = gameLogic.getBoardSize();
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create a panel for the top section
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Adding the back button to the bottom of the main panel
        JButton backButton = new JButton("Back");
        topPanel.add(backButton, BorderLayout.WEST);
        // An action listener to the back button
        backButton.addActionListener(e -> {
            // implementation of the "Back Button"
            gameLogic.undoLastMove();
            updateBoard();
        });
        // Create left and right sub-panels for the win count labels
        JPanel leftLabelPanel = new JPanel();
        JPanel rightLabelPanel = new JPanel();
        // Add attackerWinsLabel to the left sub-panel
        leftLabelPanel.add(playerTowWinsLabel);
        // Add defenderWinsLabel to the right sub-panel
        rightLabelPanel.add(playerOneWinsLabel);
        // Add the sub-panels to the top panel
        topPanel.add(leftLabelPanel, BorderLayout.WEST);
        topPanel.add(turnLabel, BorderLayout.CENTER);
        topPanel.add(rightLabelPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        // Adding the reset button to the bottom of the main panel
        JButton resetButton = new JButton("Reset");
        topPanel.add(resetButton, BorderLayout.EAST);
        // An action listener to the reset button
        resetButton.addActionListener(e -> {
            resetGame();
        });

    }

    /**
     * This function is called when two different buttons are pressed, one after the other.
     * @param srcPosition The source piece position
     * @param destPosition The destination piece position
     */
    private void twoButtonsListener(Position srcPosition, Position destPosition) {
        if (gameLogic.move(srcPosition, destPosition)) {
            updateBoard();

            // Check For victory
            if (gameLogic.isGameFinished()) {
                resetGame();
            }
        }
    }


    /**
     * Updates the game board UI to reflect the current state of the game.
     * It updates the turn label, the button text, and the text color based on the current game state.
     *
     * If it's the attacker's turn, the turn label will display "Attacker's Turn", otherwise "Defender's Turn".
     * For each cell on the board, the button's appearance and text will be updated to match the corresponding
     * piece's position and type. The text color is determined by the piece's owner.
     *
     * @see #updateWinsLabels(int, int)
     */
    private void updateBoard() {
        // Update the turn label based on the attacker's turn
        if (gameLogic.isSecondPlayerTurn()) {
            turnLabel.setText("Player 2's Turn");
        } else {
            turnLabel.setText("Player 1's Turn");
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = gameLogic.getPieceAtPosition(new Position(row, col));
                if (piece != null) {
                    // Update the button's appearance based on the new piece position
                    String type = piece.getType();
                    buttons[row][col].setText(type);
                    if ((piece.getOwner().isPlayerOne())) {
                        buttons[row][col].setForeground(Color.BLUE);
                    } else {
                        buttons[row][col].setForeground(new Color(165, 42, 42));
                    }
                } else {
                    buttons[row][col].setText("");
                }
            }
        }
        updateWinsLabels(gameLogic.getSecondPlayer().getWins(), gameLogic.getFirstPlayer().getWins());
    }
    private void updateWinsLabels(int attackerWins, int defenderWins) {
        playerTowWinsLabel.setText("♟ Player 2 Wins: " + attackerWins);
        playerOneWinsLabel.setText("♙ Player 1 Wins: " + defenderWins);
    }

    /**
     * Initializes and displays the game board with buttons for interactions.
     * Sets up button appearance, colors, and interactions with user input.
     * Highlights selected pieces and manages their movement based on user actions.
     */
    public void start() {
        // Your game logic to handle user interactions and updates
        for (int col = 0; col < BOARD_SIZE; col++) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE)); // Adjust size as needed
                Font chessFont = new Font("DejaVu Sans", Font.PLAIN, FONT_SIZE);
                buttons[row][col].setFont(chessFont);

                // Set alternating background colors for a chessboard pattern
                if ((row + col) % 2 != 0) {
                    buttons[row][col].setBackground(new Color(184,134,11));
                }
                else  {
                    buttons[row][col].setBackground(Color.WHITE);
                }
                if (row % (BOARD_SIZE - 1)  == 0 && col % (BOARD_SIZE - 1) == 0) {
                    buttons[row][col].setBackground(new Color(189,183,107));
                }

                // Clear default border and content area
                buttons[row][col].setBorderPainted(false);

                if (gameLogic.getPieceAtPosition(new Position(row, col)) != null) {
                    // Set the button's appearance based on the piece type
                    Piece piece = gameLogic.getPieceAtPosition(new Position(row, col));
                    String type = piece.getType();
                    buttons[row][col].setText(type);
                    if ((piece.getOwner().isPlayerOne())) {
                        buttons[row][col].setForeground(Color.BLUE);
                    } else {
                        buttons[row][col].setForeground(new Color(165, 42, 42));
                    }
                }

                mainPanel.add(buttons[row][col]);
                buttons[row][col].putClientProperty("row", row); // Store the row index
                buttons[row][col].putClientProperty("col", col); // Store the column index
                buttons[row][col].addActionListener((e)-> {
                    JButton clickedButton = (JButton) e.getSource();

                    if (selectedButton == null) {
                        // No button was selected before, so highlight the clicked button
                        int rowIndexOld = (int) clickedButton.getClientProperty("row");
                        int colIndexOld = (int) clickedButton.getClientProperty("col");
                        Position p = new Position(rowIndexOld, colIndexOld);
                        if (gameLogic.getPieceAtPosition(p) != null) {
                            selectedButton = clickedButton;
                            selectedColor = selectedButton.getBackground();
                            selectedButton.setBackground(Color.GREEN); // Or any other highlighting color
                        }
                    } else if (selectedButton == clickedButton) {
                        // Clicking the same button again deselects it
                        selectedButton.setBackground(selectedColor);
                        selectedButton = null;
                    } else {
                        // Deselect the previously selected button and highlight the new one
                        selectedButton.setBackground(selectedColor);
                        int rowIndexOld = (int) selectedButton.getClientProperty("row");
                        int colIndexOld = (int) selectedButton.getClientProperty("col");
                        Position oldPosition = new Position(rowIndexOld, colIndexOld);

                        selectedButton = null;

                        int rowIndex = (int) clickedButton.getClientProperty("row");
                        int colIndex = (int) clickedButton.getClientProperty("col");
                        Position newPosition = new Position(rowIndex, colIndex);

                        twoButtonsListener(oldPosition, newPosition);
                    }
                });
            }
        }

        // Add the main panel to the frame
        getContentPane().add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void resetGame() {
        gameLogic.reset();

        // Reset UI elements
        updateBoard();
        turnLabel.setText("Player 2's Turn");

        // Reset selectedButton and selectedColor
        if (selectedButton != null) {
            selectedButton.setBackground(selectedColor);
            selectedButton = null;
        }
    }
}
