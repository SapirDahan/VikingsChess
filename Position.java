import java.util.HashSet;
import java.util.Set;

public class Position {

    private final int row;
    private final int col;
    private Piece piece; //The piece on the position
    private int diedOnPosition = 0; //At first no one died on any position

    //set of all the piece that had been on the position
    Set<ConcretePiece> uniqueElementsOnPosition = new HashSet<>();

    //Constructor
    public Position (int col, int row){
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    //Get the row of the position
    public int getRow(){
        return this.row;
    }

    //Get the column of the position
    public int getCol() {
        return this.col;
    }

    //Get the piece on the position
    public Piece getPiece(){
        return this.piece;
    }

    //Set the piece on the position
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    //Get how many pieces had died on the position
    public int getDiedOnPosition() {
        return diedOnPosition;
    }

    //Set the number of piece that had died on the position
    public void setDiedOnPosition(int diedOnPosition) {
        this.diedOnPosition = diedOnPosition;
    }

    //Increase the number of pieces that had died on the position
    public void increaseDeadCount(){
        diedOnPosition++;
    }

    //Add element to set
    public void addToSet(ConcretePiece p){
        uniqueElementsOnPosition.add(p);
    }

    //Remove an element from the set
    public void removeElementFromSet(ConcretePiece p){
        uniqueElementsOnPosition.remove(p);
    }

    //Get the size of the set
    public int getElementsOnSet(){
        return uniqueElementsOnPosition.size();
    }
}
