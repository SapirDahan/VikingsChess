# VikingsChess
Hnefatafl is a strategic board game that is played on an 11 x 11 grid of squares. 
The game is typically accompanied by 24 dark pieces (in our case red) and 13 light pieces(in our case blue), usually in the form of pawns, along with an additional King piece (only to the defending player).
The objective of the game is to strategically move the pieces across the board in order to capture the opponent's pieces and ultimately trap their King or to get the king to a corner.
Hnefatafl is a game characterized by its unequal sides and distinct objectives. 
The placement of the King in the center of the board, surrounded by red defending pieces in a specific pattern, sets the stage for the strategic gameplay.
Additionally, the positioning of the blue pieces in four groups, arranged in a particular formation at the middle of each edge of the board.<br />
![board](/images/board.png)
# Table of Contents
1. [Winning The Game](#winning-the-game)
    * [Defending Player](#defending-player)
    * [Attacking Player](#attacking-player)
3. [Game Rules](#game-rules)
4. [The Information On The Finishing Game](#the-information-on-the-finishing-game)

## Winning The Game
### Defending Player
The objective of the side playing with the blues pieces is to maneuver the King to a corner square of the board in order to win the game.<br />
![KingAtCorner](/images/KingAtCorner.png)

### Attacking Player
The objective of the attacking side is to eliminate the King before he manages to reach a corner. The King can be captured by surrounding him with opposing pieces on all four sides, or if the King is positioned at the edge of the board, on the three available sides.<br />
![CapturingKing4](/images/CapturingKing4.png)
![CapturingKing3](/images/CapturingKing3.png)

## Game Rules
The rules of the game are as follows: Only the King is permitted to occupy the corner squares, and such a move results in winning the game for the deffending player. The attacking side makes the first move. All pieces move in a manner similar to the rook in chess, advancing in a straight line across as many empty squares as the player desires. It is important to note that pieces are unable to leap over other pieces and are restricted from moving diagonally.
The act of capturing in this game occurs when a player moves a piece in such a way that it traps a single opposing piece between two of their own pieces. Once captured, the piece is immediately removed from the board. It is important to note that it is possible to capture more than one piece at once. Additionally, the King is unable to capture, even if a mate pawn initiated the attack.
The rule for capturing a piece next to the corner square or a wall in the game is that a single opposing piece can capture it. The capturing piece is moved so that the opposing piece becomes trapped between the corner or a wall and the capturing piece. It is important to note that in order to capture the king, at least three pieces of the attacker are needed, as it will not work with fewer pieces.
A piece can move to a square between two of the opponent's pieces without being captured.

## The Information On The Finishing Game
At the conclusion of each game, we receive detailed information regarding the specific events that occurred during the game on the terminal.<br />
![conclution](/images/conclution.png)
