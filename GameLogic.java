import java.util.*;

public class GameLogic implements PlayableLogic{

    private final int boardSize = 11; //The board size
    private Position board[][] = new Position[boardSize][boardSize]; //Creating the board
    private ConcretePlayer player1 = new ConcretePlayer(true); //Create player 1
    private ConcretePlayer player2 = new ConcretePlayer(false); //Create player 2
    private int numOfSteps = 0; //Initialize the number of steps in the game to 0
    private Stack<Integer[]> moves= new Stack<>(); //Storing all the data of the game
    private final int numOfElements = 13 + 24; //number of elements in the game
    private ConcretePlayer whoWonLast = null; //Get the player who won last

    //The king
    private King K7 = new King(player1);

    //Play1 pawns
    private Pawn D1 = new Pawn(player1);
    private Pawn D2 = new Pawn(player1);
    private Pawn D3 = new Pawn(player1);
    private Pawn D4 = new Pawn(player1);
    private Pawn D5 = new Pawn(player1);
    private Pawn D6 = new Pawn(player1);
    private Pawn D8 = new Pawn(player1);
    private Pawn D9 = new Pawn(player1);
    private Pawn D10 = new Pawn(player1);
    private Pawn D11 = new Pawn(player1);
    private Pawn D12 = new Pawn(player1);
    private Pawn D13 = new Pawn(player1);

    //Player2 pawns
    private Pawn A1 = new Pawn(player2);
    private Pawn A2 = new Pawn(player2);
    private Pawn A3 = new Pawn(player2);
    private Pawn A4 = new Pawn(player2);
    private Pawn A5 = new Pawn(player2);
    private Pawn A6 = new Pawn(player2);
    private Pawn A7 = new Pawn(player2);
    private Pawn A8 = new Pawn(player2);
    private Pawn A9 = new Pawn(player2);
    private Pawn A10 = new Pawn(player2);
    private Pawn A11 = new Pawn(player2);
    private Pawn A12 = new Pawn(player2);
    private Pawn A13 = new Pawn(player2);
    private Pawn A14 = new Pawn(player2);
    private Pawn A15 = new Pawn(player2);
    private Pawn A16 = new Pawn(player2);
    private Pawn A17 = new Pawn(player2);
    private Pawn A18 = new Pawn(player2);
    private Pawn A19 = new Pawn(player2);
    private Pawn A20 = new Pawn(player2);
    private Pawn A21 = new Pawn(player2);
    private Pawn A22 = new Pawn(player2);
    private Pawn A23 = new Pawn(player2);
    private Pawn A24 = new Pawn(player2);


    //Array of all pieces (including the king)
    ConcretePiece[] allPieces = {D1, D2, D3, D4, D5, D6, K7, D8, D9, D10, D11,
            D12, D13, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15,
            A16, A17, A18, A19, A20, A21, A22, A23, A24};

    //Array of all pawns (all elements except the king)
    Pawn[] allPawns = {D1, D2, D3, D4, D5, D6, D8, D9, D10, D11,
            D12, D13, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15,
            A16, A17, A18, A19, A20, A21, A22, A23, A24};

    //Array of all the defenders
    ConcretePiece[] defenders = {D1, D2, D3, D4, D5, D6, K7, D8, D9, D10, D11,
            D12, D13};

    //Array of all the attackers
    ConcretePiece[] attackers = {A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13,
            A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24};


    //Constructor
    public GameLogic(){

        //Initialize the board with nulls (no pieces)
        for(int col = 0; col < boardSize; col++){
            for (int row = 0; row < boardSize; row++){
                board[col][row] = new Position(col, row);
            }
        }

        //Adding the king to the board
        createKingFirstPosition();

        //Adding all the pawns to the board
        createPawnsFirstPosition();

        //Save the board
        saveMove();
    }

    //Checking if the move is legal
    @Override
    public boolean move(Position a, Position b) {

        //The move is legal?
        boolean legalMove = false;

        //If there is no piece at position a (then we can't move an element)
        if (getPieceAtPosition(a) == null) {
            return false;
        }

        //Checking if the piece belong to player who have the turn
        if (getPieceAtPosition(a).getOwner().equals(getFirstPlayer()) && isSecondPlayerTurn()) {
            return false;
        }

        if (getPieceAtPosition(a).getOwner().equals(getSecondPlayer()) && !isSecondPlayerTurn()) {
            return false;
        }

        //Checking if the moving piece is the king
        Boolean isKing = (getPieceAtPosition(a).getType().equals(K7.getType()));

        //Only the king can move to the corners
        if (!isKing && (b.getRow() == 0 || b.getRow() == boardSize - 1) && (b.getCol() == 0 || b.getCol() == boardSize - 1)) {
            return false; //Illegal move
        }


        //Finding the way the piece is moving and checking if it legal

        //If the piece is moving horizontally
        if(a.getRow() == b.getRow()) {
            if (a.getCol() > b.getCol()) {
                for (int i = 1; i <= (a.getCol() - b.getCol()); i++) {
                    if (getPieceAtPosition(new Position(a.getCol() - i, a.getRow())) != null) {
                        return false;
                    }
                }
                legalMove = true;
            }


            if (a.getCol() < b.getCol()) {
                for (int i = 1; i <= (b.getCol() - a.getCol()); i++) {
                    if (getPieceAtPosition(new Position(a.getCol() + i, a.getRow())) != null) {
                        return false;
                    }
                }
                legalMove = true;;
            }
        }

        //If the piece is moving vertically
        if(a.getCol() == b.getCol()) {
            if (a.getRow() > b.getRow()) {
                for (int i = 1; i <= (a.getRow() - b.getRow()); i++) {
                    if (getPieceAtPosition(new Position(a.getCol(), a.getRow() - i)) != null) {
                        return false;
                    }
                }
                legalMove = true;;
            }


            if (a.getRow() < b.getRow()) {
                for (int i = 1; i <= (b.getRow() - a.getRow()); i++) {
                    if (getPieceAtPosition(new Position(a.getCol(), a.getRow() + i)) != null) {
                        return false;
                    }
                }
                legalMove = true;;
            }
        }

        //If the move is legal update the board
        if(legalMove){

            //Find the piece at location a
            for(ConcretePiece p : allPieces){
                if(samePosition(p.getPosition(), a) && p.isAlive()){
                    p.addMove(b);
                    board[b.getCol()][b.getRow()].addToSet(p); //Adding a piece for the position history set
                    break; //Only one to find no need to continue search
                }
            }


            //Change the location of the piece
            board[b.getCol()][b.getRow()].setPiece(getPieceAtPosition(a));
            board[a.getCol()][a.getRow()].setPiece(null);

            //Checking if pawns had died in that move
            //The king can't kill
            if(!isKing){
                findDeadPawnAtMove(b);
            }

            //Save the move
            saveMove();

            //If the game finished print the information on that game
            if(isGameFinished()){
                whoWonLast.anotherWin();
                print();
            }

            //Increase the number of moves
            numOfSteps++;

            //The move is legal so return true
            return true;
        }

        //The move is illegal because it is not on the same line
        return false;
    }

    //Get the piece at a given position
    @Override
    public Piece getPieceAtPosition(Position position) {
        return board[position.getCol()][position.getRow()].getPiece();
    }

    //Return the first player
    @Override
    public Player getFirstPlayer() {
        return player1;
    }

    //Return the second player
    @Override
    public Player getSecondPlayer() {
        return player2;
    }

    //Find up if the game has finished
    @Override
    public boolean isGameFinished() {

        //Get the position of the king
        Position kingPosition = K7.getPosition();


        //Checking if the king arrived to a corner  If he does then change flag to true
        if ((kingPosition.getRow() == 0 || kingPosition.getRow() == boardSize - 1) && (kingPosition.getCol() == 0 || kingPosition.getCol() == boardSize - 1)) {

            //Player 1 had win this game
            whoWonLast = player1;
            return true;
        }

        //get the col and row
        int row = kingPosition.getRow();
        int col = kingPosition.getCol();

        //Checking if the king is at side and surrounded with 3 pawns of player2
        if (row == 0 || row == boardSize - 1 || col == 0 || col == boardSize - 1) {
            if (countEnemiesSurroundingTheKing(col, row) == 3) {
                whoWonLast = player2;
                return true;
            }
        }

        //Checking if the king sounder by 4 pawns of player2
        if (countEnemiesSurroundingTheKing(col, row) == 4) {
            whoWonLast = player2;
            return true;
        }

        //If only 2 pawns of player 2 are alive then it is a victory for player 1
        int count = 0;
        for (ConcretePiece p : allPieces) {
            if (p.getOwner().equals(getSecondPlayer()) && p.isAlive()) {
                count++;
            }
        }

        //If player two had left 2 pawns he can't win
        if (count == 2) {
            whoWonLast = player1;
            return true;
        }

        //Return if the game is not over
        return false;
    }

    //Is it second player turn
    @Override
    public boolean isSecondPlayerTurn() {

        //The second player have to evens turns and the first player have the odds (starting from 0)
        return numOfSteps % 2 == 0;
    }

    //Reset the board
    @Override
    public void reset() {

        //Reset the steps
        numOfSteps = 0;

        //Delete all the previous moves on the board
        moves.clear();

        //Set all the board to null
        for(int row = 0; row < boardSize; row++){
            for (int col = 0; col < boardSize; col++){
                board[row][col] = new Position(row, col);
            }
        }

        //Set all the information on the pieces
        for(ConcretePiece piece: allPieces){
            piece.setKilled(0); //Set kills
            piece.resetMoves(); //Reset moves
            piece.setAlive(true); //At the beginning all the pieces are alive
        }

        //Place all the elements to there first position
        createKingFirstPosition();
        createPawnsFirstPosition();

    }

    //Undoing the last move
    @Override
    public void undoLastMove() {

        //If there were no moves yet
        if(moves.size() == 1){
            return;
        }

        //Goes a step back
        numOfSteps--;

        //Remove last element from the stack;
        Integer[] lastMove = moves.pop();

        //Get the move before that
        Integer[] previousToLast = moves.peek();

        //It is possible that 3 pawns at most had died on the move
        //Find the positions that pawns had died on
        int[] arr = {-1, -1, -1};

        //Moving over the board and check if a pawn had died on that position from the move
        for(int i = numOfElements * 3; i < numOfElements * 3 + boardSize * boardSize; i++){

            //If it is not equal then a pawn had died on that position
            if(lastMove[i] != previousToLast[i]){
                boolean foundNegativeOne = false; //A flag for finding the first -1

                //Over the arr
                for(int j = 0; j < 3; j++){
                    if(arr[j] == -1 && !foundNegativeOne){
                        arr[j] = i - (numOfElements * 3); //The position that a pawn had died on that move
                        foundNegativeOne = true;
                    }
                }
            }
        }

        //Bring back to live the pawns that had died on the move
        //Move over arr
        for(int i = 0; i < 3; i++){

            //If there is still a position that a pawn had died
            if(arr[i] != -1){

                int col = arr[i] / boardSize; //Find the column
                int row = arr[i] % boardSize; //find the row

                //find the position on the board the position
                Position position = board[col][row];

                //Find the element that had died on that move
                for(int j = 0; j < numOfElements * 3; j+=3){

                    //If that the piece that had the same position on the move before the last one
                    if(previousToLast[j] == col && previousToLast[j+1] == row){
                        allPieces[j / 3].setAlive(true); //Set the piece to be alive again
                        position.setPiece(allPieces[j / 3]); //Set the piece on that position
                        position.addToSet(allPieces[j / 3]); //Add the piece to the set of elements on the position

                        //Decrease the count of dead's on the position
                        position.setDiedOnPosition(position.getDiedOnPosition() - 1);
                    }
                }
            }
        }

        //Set the piece that moved to his former place
        for(int i = 0; i < numOfElements; i++){

            //If the position of the piece had changed and the piece had not died then this is the piece we are looking for
            if((previousToLast[i*3] != lastMove[i*3] || previousToLast[i*3+1] != lastMove[i*3+1]) && lastMove[i*3] != -1) {

                //Set the former place of the piece to null
                board[allPieces[i].getPosition().getCol()][allPieces[i].getPosition().getRow()].setPiece(null);
                allPieces[i].deleteLastMove(); //Delete the last move of the piece that had moved

                //If the piece were on that position only the time we are doing the move back
                if(!allPieces[i].getHistoryMoves().contains(board[allPieces[i].getPosition().getCol()][allPieces[i].getPosition().getRow()])){

                    //Then delete it from the set of elements that had been on that position
                    board[allPieces[i].getPosition().getCol()][allPieces[i].getPosition().getRow()].removeElementFromSet(allPieces[i]);
                }

                //Set the piece to his former place
                board[previousToLast[i*3]][previousToLast[i*3 + 1]].setPiece(allPieces[i]);

                //Decrease the number the element had killed
                for(int j = 0; j < 3; j++){

                    //While a piece had died from that move
                    if(arr[j] != -1){

                        //Decrease the number ogg kills the piece had done
                        allPieces[i].decreaseKills();
                    }
                }
            }
        }
    }

    //Get board size
    @Override
    public int getBoardSize() {
        return boardSize;
    }

    //Get the player who have the turn now
    public Player getPlayer(){

        //return player2 if it is his turn and player1 if it is his turn
        return (isSecondPlayerTurn())? player2: player1;
    }


    //Find who had died at a move
    public void findDeadPawnAtMove(Position a){

        Player player;
        Player enemy;

        //Finding out who is the player and who is the enemy
        if(isSecondPlayerTurn()){
            player = getSecondPlayer();
            enemy = getFirstPlayer();
        }

        else {
            player = getFirstPlayer();
            enemy = getSecondPlayer();
        }

        int col = a.getCol(); //Get a column
        int row = a.getRow(); //Get a row

        Pawn[] fourSides = {null, null, null, null};

        //Finding out if the right piece (if exist) had died on that move
        if((col + 1 < boardSize) && !samePosition(board[col + 1][row], K7.getPosition())){
            if(((board[col + 1][row].getPiece() != null) && (getPieceAtPosition(board[col + 1][row]).getOwner().equals(enemy)))){
                if(col + 2 == boardSize || ((board[col + 2][row].getPiece() != null) &&
                getPieceAtPosition(board[col + 2][row]).getOwner().equals(player) && !samePosition(board[col + 2][row], K7.getPosition()))
                || ((col + 2 == boardSize - 1) && (row == 0 || row == boardSize - 1))){
                    fourSides[0] = (Pawn) getPieceAtPosition(board[col + 1][row]);
                }
            }
        }

        //Finding out if the bottom piece (if exist) had died on that move
        if((row - 1 >= 0) && !samePosition(board[col][row - 1], K7.getPosition())){
            if(board[col][row - 1].getPiece() != null && getPieceAtPosition(board[col][row - 1]).getOwner().equals(enemy)){
                if(row - 2 == -1 || ((board[col][row - 2].getPiece() != null) &&
                 getPieceAtPosition(board[col][row - 2]).getOwner().equals(player) && !samePosition(board[col][row - 2], K7.getPosition()))
                || ((row - 2 == 0) && (col == 0 || col == boardSize - 1))){
                    fourSides[1] = (Pawn) getPieceAtPosition(board[col][row - 1]);
                }
            }
        }

        //Finding out if the left piece (if exist) had died on that move
        if((col - 1 >= 0) && !samePosition(board[col - 1][row], K7.getPosition())){
            if(board[col - 1][row].getPiece() != null && getPieceAtPosition(board[col - 1][row]).getOwner().equals(enemy)){
                if(col - 2 == -1 || ((board[col - 2][row]).getPiece() != null &&
                getPieceAtPosition(board[col - 2][row]).getOwner().equals(player)&& !samePosition(board[col - 2][row], K7.getPosition()))
                || ((col - 2 == 0) && (row == 0 || row == boardSize - 1))) {
                    fourSides[2] = (Pawn) getPieceAtPosition(board[col - 1][row]);
                }
            }
        }

        //Finding out if the above piece (if exist) had died on that move
        if((row + 1 < boardSize) && !samePosition(board[col][row + 1], K7.getPosition())){
            if((board[col][row + 1]).getPiece() != null && getPieceAtPosition(board[col][row + 1]).getOwner().equals(enemy)){
                if(row + 2 == boardSize || ((board[col][row + 2]).getPiece() != null &&
                        getPieceAtPosition(board[col][row + 2]).getOwner().equals(player) && !samePosition(board[col][row + 2], K7.getPosition()))
                || ((row + 2 == boardSize - 1) && (col == 0 || col == boardSize - 1))) {
                    fourSides[3] = (Pawn) getPieceAtPosition(board[col][row + 1]);
                }
            }
        }


        //Kill the pieces in the array
        for(int i = 0; i < 4; i++){
            if(fourSides[i] != null){
                Pawn p = fourSides[i];
                p.setAlive(false); //p is now died

                //Increase the kills for the ConcretePiece at position a
                ((ConcretePiece) getPieceAtPosition(a)).increaseKills();

                //Increase the number of elements that had died on the position
                board[p.getPosition().getCol()][p.getPosition().getRow()].increaseDeadCount();

                //Removing piece from board
                board[p.getPosition().getCol()][p.getPosition().getRow()].setPiece(null);
            }
        }
    }

    //Create the king first position
    public void createKingFirstPosition(){
        board[5][5].setPiece(K7); //Set the king on the board
        K7.addMove(new Position(5, 5)); //Add the first position to the history of the king
        K7.setName("K7"); //Set the king name
        board[5][5].addToSet(K7); //Add the king to the set of pieces being on that position
    }

    //Create the pawns positions
    public void createPawnsFirstPosition(){

        /*
        For all the pawns set them on the board,
        add that position to there history moves'
        set there names
        And add them to the set of pieces being on that position
        */

        //Create the position for pwans of player 1
        board[5][3].setPiece(D1);
        D1.addMove(new Position(5,3));
        D1.setName("D1");
        board[5][3].addToSet(D1);

        board[4][4].setPiece(D2);
        D2.addMove(new Position(4, 4));
        D2.setName("D2");
        board[4][4].addToSet(D2);

        board[5][4].setPiece(D3);
        D3.addMove(new Position(5,4));
        D3.setName("D3");
        board[5][4].addToSet(D3);

        board[6][4].setPiece(D4);
        D4.addMove(new Position(6,4));
        D4.setName("D4");
        board[6][4].addToSet(D4);

        board[3][5].setPiece(D5);
        D5.addMove(new Position(3,5));
        D5.setName("D5");
        board[3][5].addToSet(D5);

        board[4][5].setPiece(D6);
        D6.addMove(new Position(4,5));
        D6.setName("D6");
        board[4][5].addToSet(D6);

        board[6][5].setPiece(D8);
        D8.addMove(new Position(6,5));
        D8.setName("D8");
        board[6][5].addToSet(D8);

        board[7][5].setPiece(D9);
        D9.addMove(new Position(7,5));
        D9.setName("D9");
        board[7][5].addToSet(D9);

        board[4][6].setPiece(D10);
        D10.addMove(new Position(4,6));
        D10.setName("D10");
        board[4][6].addToSet(D10);

        board[5][6].setPiece(D11);
        D11.addMove(new Position(5,6));
        D11.setName("D11");
        board[5][6].addToSet(D11);

        board[6][6].setPiece(D12);
        D12.addMove(new Position(6, 6));
        D12.setName("D12");
        board[6][6].addToSet(D12);

        board[5][7].setPiece(D13);
        D13.addMove(new Position(5,7));
        D13.setName("D13");
        board[5][7].addToSet(D13);


        //Create the position for pwans of player 2
        board[3][0].setPiece(A1);
        A1.addMove(new Position(3,0));
        A1.setName("A1");
        board[3][0].addToSet(A1);

        board[4][0].setPiece(A2);
        A2.addMove(new Position(4,0));
        A2.setName("A2");
        board[4][0].addToSet(A2);

        board[5][0].setPiece(A3);
        A3.addMove(new Position(5,0));
        A3.setName("A3");
        board[5][0].addToSet(A3);

        board[6][0].setPiece(A4);
        A4.addMove(new Position(6,0));
        A4.setName("A4");
        board[6][0].addToSet(A4);

        board[7][0].setPiece(A5);
        A5.addMove(new Position(7,0));
        A5.setName("A5");
        board[7][0].addToSet(A5);

        board[5][1].setPiece(A6);
        A6.addMove(new Position(5,1));
        A6.setName("A6");
        board[5][1].addToSet(A6);

        board[0][3].setPiece(A7);
        A7.addMove(new Position(0,3));
        A7.setName("A7");
        board[0][3].addToSet(A7);

        board[10][3].setPiece(A8);
        A8.addMove(new Position(10,3));
        A8.setName("A8");
        board[10][3].addToSet(A8);

        board[0][4].setPiece(A9);
        A9.addMove(new Position(0,4));
        A9.setName("A9");
        board[0][4].addToSet(A9);

        board[10][4].setPiece(A10);
        A10.addMove(new Position(10,4));
        A10.setName("A10");
        board[10][4].addToSet(A10);

        board[0][5].setPiece(A11);
        A11.addMove(new Position(0,5));
        A11.setName("A11");
        board[0][5].addToSet(A11);

        board[1][5].setPiece(A12);
        A12.addMove(new Position(1,5));
        A12.setName("A12");
        board[1][5].addToSet(A12);

        board[9][5].setPiece(A13);
        A13.addMove(new Position(9,5));
        A13.setName("A13");
        board[9][5].addToSet(A13);

        board[10][5].setPiece(A14);
        A14.addMove(new Position(10,5));
        A14.setName("A14");
        board[10][5].addToSet(A14);

        board[0][6].setPiece(A15);
        A15.addMove(new Position(0,6));
        A15.setName("A15");
        board[0][6].addToSet(A15);

        board[10][6].setPiece(A16);
        A16.addMove(new Position(10,6));
        A16.setName("A16");
        board[10][6].addToSet(A16);

        board[0][7].setPiece(A17);
        A17.addMove(new Position(0,7));
        A17.setName("A17");
        board[0][7].addToSet(A17);

        board[10][7].setPiece(A18);
        A18.addMove(new Position(10,7));
        A18.setName("A18");
        board[10][7].addToSet(A18);

        board[5][9].setPiece(A19);
        A19.addMove(new Position(5,9));
        A19.setName("A19");
        board[5][9].addToSet(A19);

        board[3][10].setPiece(A20);
        A20.addMove(new Position(3,10));
        A20.setName("A20");
        board[3][10].addToSet(A20);

        board[4][10].setPiece(A21);
        A21.addMove(new Position(4,10));
        A21.setName("A21");
        board[4][10].addToSet(A21);

        board[5][10].setPiece(A22);
        A22.addMove(new Position(5,10));
        A22.setName("A22");
        board[5][10].addToSet(A22);

        board[6][10].setPiece(A23);
        A23.addMove(new Position(6,10));
        A23.setName("A23");
        board[6][10].addToSet(A23);

        board[7][10].setPiece(A24);
        A24.addMove(new Position(7,10));
        A24.setName("A24");
        board[7][10].addToSet(A24);

    }

    //Checking if two positions are at the same place
    public boolean samePosition(Position a, Position b){
        return (a.getRow() == b.getRow()) && (a.getCol() == b.getCol());
    }

    //Count how many of the pawns of player2 surrounding the king
    public int countEnemiesSurroundingTheKing(int col, int row){

        int count = 0;

        //Check if the place is legal and if player2 is there increase the counter

        if (col - 1 >= 0 && getPieceAtPosition(new Position(col - 1, row)) != null && getPieceAtPosition(new Position(col - 1, row)).getOwner().equals(player2)) {
            count++;
        }

        if (col + 1 < boardSize && getPieceAtPosition(new Position(col + 1, row)) != null && getPieceAtPosition(new Position(col + 1, row)).getOwner().equals(player2)) {
            count++;
        }

        if (row - 1 >= 0 && getPieceAtPosition(new Position(col, row - 1)) != null && getPieceAtPosition(new Position(col, row - 1)).getOwner().equals(player2)) {
            count++;
        }

        if (row + 1 < boardSize && getPieceAtPosition(new Position(col, row + 1)) != null && getPieceAtPosition(new Position(col,row + 1)).getOwner().equals(player2)) {
            count++;
        }
        return count;
    }

    //Check if 2 positions are near each others
    public boolean twoNearPoints(Position a, Position b){
        if(a.getRow() == b.getRow()){
            return Math.abs(a.getCol() - b.getCol()) == 1;
        }

        if(a.getCol() == b.getCol()){
            return Math.abs(a.getRow() - b.getRow()) == 1;
        }

        return false;
    }

    //save the move
    public void saveMove(){
        Integer[] arr = new Integer[boardSize * boardSize + numOfElements * 3];

        //For all the elements put there cols and rows and if there are alive
        //If alive put 1 and if not put 0 add set the col and row to -1
        for(int i = 0; i < numOfElements * 3; i+=3){
            arr[i] = allPieces[i / 3].getPosition().getCol();
            arr[i + 1] = allPieces[i / 3].getPosition().getRow();

            if(allPieces[i / 3].isAlive()){
                arr[i + 2] = 1;
            }

            else{
                arr[i + 2] = 0;
                arr[i] = -1;
                arr[i + 1] = -1;
            }
        }

        //For all the positions get the number elements that died there
        for (int i = numOfElements * 3; i < numOfElements * 3 + boardSize * boardSize; i++) {
            int j = i - (numOfElements * 3); //The position (from left to right and down)

            //Put at the position place how many had died on that position
            arr[i] = board[j / boardSize][j % boardSize].getDiedOnPosition();
        }

        //Push the move to the moves stack
        moves.push(arr);
    }

    //Print the information about each game when it's finished
    public void print(){

        Player winner = getPlayer(); //Get the winner
        ConcretePiece[] winnersElements; //Elements of the winner
        ConcretePiece[] loserElements; //elements of the loser

        //The winner is player1?
        if(winner.isPlayerOne()){
            winnersElements = defenders;
            loserElements = attackers;
        }

        //the winner is player2?
        else{
            winnersElements = attackers;
            loserElements = defenders;
        }

        //Print all kind of sorts and print between them 75 dots
        sortedMoves(winnersElements);
        printSortedMoves(winnersElements);
        sortedMoves(loserElements);
        printSortedMoves(loserElements);
        print75Dots();
        printSortedKills(allPieces, winner);
        print75Dots();
        printSortedDistance(allPieces, winner);
        print75Dots();
        printSortedWalkedOnPosition();
        print75Dots();
    }

    //print a line of 75 dots
    public void print75Dots(){
        for(int i = 0; i < 74; i++){
            System.out.print("*");
        }

        System.out.println("*");
    }

    //Sort by the moves length (then by number)
    public void sortedMoves(ConcretePiece[] arr){
        Arrays.sort(arr, new Comparator<ConcretePiece>() {

            //Comparator
            @Override
            public int compare(ConcretePiece p1, ConcretePiece p2) {

                //Compare by size
                if(p1.getHistoryMoves().size() > p2.getHistoryMoves().size()){
                    return 1;
                }

                else if (p1.getHistoryMoves().size() < p2.getHistoryMoves().size()) {
                    return -1;
                }

                //if the sizes are equal then sort by number
                int p1_getNameNumber = Integer.parseInt(p1.getName().substring(1));
                int p2_getNameNumber = Integer.parseInt(p2.getName().substring(1));

                if (p1_getNameNumber > p2_getNameNumber) {
                    return 1;
                }

                if (p1_getNameNumber < p2_getNameNumber) {
                    return -1;
                }

                return 0;
            }
        });
    }

    //Print in the format the sorted array
    public void printSortedMoves(ConcretePiece[] arr) {
        for (ConcretePiece piece : arr) {
            List<Position> list = piece.getHistoryMoves();

            //Don't print the piece if it hadn't moved at all
            if (list.size() == 1) {
                continue;
            }

            System.out.print(piece.getName() + ": [");

            //Looking at all the positions of the piece
            Position lastPosition = list.get(list.size() - 1);
            for (int i = 0; i < list.size(); i++) {
                System.out.print("(" + list.get(i).getCol() + ", " + list.get(i).getRow() + ")");

                //If not last element
                if (i != list.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    //Compare and print the number of pieces the pieces had killed (if equals the compare by number and if that equals compare by winning player)
    public void printSortedKills(ConcretePiece[] arr, Player winner){
        Arrays.sort(arr, new Comparator<ConcretePiece>() {

            //Comparator
            @Override
            public int compare(ConcretePiece k1, ConcretePiece k2) {
                if (k1.getKilled() > k2.getKilled()) {
                    return -1;
                }

                if (k1.getKilled() < k2.getKilled()) {
                    return 1;
                }

                int k1_getNameNumber = Integer.parseInt(k1.getName().substring(1));
                int k2_getNameNumber = Integer.parseInt(k2.getName().substring(1));

                if (k1_getNameNumber > k2_getNameNumber) {
                    return 1;
                }

                if (k1_getNameNumber < k2_getNameNumber) {
                    return -1;
                }

               if(k1.getOwner() == winner){
                   return -1;
               }

               return 1;
            }
        });

        //Print the piece only if it killed at least 1
        for (ConcretePiece piece : arr) {
            if (piece.getKilled() == 0) {
                continue;
            }

            System.out.println(piece.getName() + ": " + piece.getKilled() + " kills");
        }
    }

    //Compare and print the pieces by the distance they had done (if equals the compare by number and if that equals compare by winning player)
    public void printSortedDistance(ConcretePiece[] arr, Player winner){

        //Set the distance for each element

        //Look over all the moves of the piece
        for (ConcretePiece piece : allPieces) {
            List<Position> moves = piece.getHistoryMoves();

            //Find the distance between all the moves
            for (int i = 0; i < moves.size() - 1; i++) {
                Position move1 = moves.get(i);
                Position move2 = moves.get(i + 1);
                int dx = move2.getCol() - move1.getCol();
                int dy = move2.getRow() - move1.getRow();
                piece.increaseDistance(Math.abs(dx) + Math.abs(dy));
            }

        }
        Arrays.sort(arr, new Comparator<ConcretePiece>() {

            //comparator
            @Override
            public int compare(ConcretePiece d1, ConcretePiece d2) {
                if (d1.getDistance() > d2.getDistance()) {
                    return -1;
                }

                if (d1.getDistance() < d2.getDistance()) {
                    return 1;
                }

                int d1_getNameNumber = Integer.parseInt(d1.getName().substring(1));
                int d2_getNameNumber = Integer.parseInt(d2.getName().substring(1));

                if (d1_getNameNumber > d2_getNameNumber) {
                    return 1;
                }

                if (d1_getNameNumber < d2_getNameNumber) {
                    return -1;
                }

                if(d1.getOwner() == winner){
                    return -1;
                }

                return 1;
            }
        });


        //Print the pieces only if they had moved
        for (ConcretePiece piece : arr) {
            if (piece.getDistance() == 0) {
                continue;
            }

            System.out.println(piece.getName() + ": " + piece.getDistance() + " squares");
        }
    }

    //Sort and print how many different elements had been on the position (if equals sort by X and if that equal sort by Y)
    public void printSortedWalkedOnPosition(){

        //Array of all the positions on the board
        Position[] arr = new Position[boardSize * boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                arr[i * boardSize + j] = new Position(i, j);
            }
        }

        //Comparator
        Arrays.sort(arr, new Comparator<Position>() {
            @Override
            public int compare(Position p1, Position p2) {
                if(board[p1.getCol()][p1.getRow()].getElementsOnSet() > board[p2.getCol()][p2.getRow()].getElementsOnSet()) {
                    return -1;
                }

                if(board[p1.getCol()][p1.getRow()].getElementsOnSet() < board[p2.getCol()][p2.getRow()].getElementsOnSet()) {
                    return 1;
                }

                if(p1.getCol() > p2.getCol()){
                    return 1;
                }

                if(p1.getCol() < p2.getCol()){
                    return -1;
                }

                if(p1.getRow() > p2.getRow()){
                    return 1;
                }

                if(p1.getRow() < p2.getRow()){
                    return -1;
                }

                return 0;
            }
        });

        //If the number of elements is more than 1 then print the positions
        for (Position p : arr) {
            if(board[p.getCol()][p.getRow()].getElementsOnSet() > 1){
                System.out.println("(" + p.getCol() + ", " + p.getRow() + ")"+ board[p.getCol()][p.getRow()].getElementsOnSet() + " pieces");
            }
        }
    }
}

