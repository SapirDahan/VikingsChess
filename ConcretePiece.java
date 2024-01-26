import java.util.ArrayList;
import java.util.List;

public abstract class ConcretePiece implements Piece{
    private Player owner;
    private String type;
    private boolean alive = true;
    private List<Position> historyMoves = new ArrayList<>();
    private String name = "";
    private int killed = 0;
    private int distance = 0;

    //Constructor
    public ConcretePiece() {}

    //Set the type of the piece
    public void setType(String type) {
        this.type = type;
    }

    //Get the owner of the piece
    @Override
    public Player getOwner() {
        return owner;
    }

    //Get the type of the piece
    @Override
    public String getType() {
        return type;
    }

    //Set the piece to be alive
    public void setAlive(boolean alive){
        this.alive = alive;
    }

    //Return if the piece is alive
    public boolean isAlive() {
        return alive;
    }

    //Get the moves the piece had done over the current game
    public List<Position> getHistoryMoves() {
        return historyMoves;
    }

    //Add a move to the collection of moves of the piece
    public void addMove(Position move) {
        historyMoves.add(move);
    }

    //Remove the last move from the memory of the history moves
    public void deleteLastMove(){
        if(!historyMoves.isEmpty()){
            historyMoves.removeLast();
        }
    }

    //Get the position the piece is currently on
    public Position getPosition(){
        if(!historyMoves.isEmpty()){
            return historyMoves.getLast();
        }
        return null;
    }

    //Clear all the history moves of the piece
    public void resetMoves(){
        historyMoves.clear();
    }

    //Get the name of the piece
    public String getName() {
        return name;
    }

    //Set the name of the piece
    public void setName(String name) {
        this.name = name;
    }

    //Increase the count of how many piece the current piece had killed
    public void increaseKills(){
        killed++;
    }

    //Decrease the count of how many piece the current piece had killed
    public void decreaseKills(){
        killed--;
    }

    //Get the count of how many piece the current piece had killed
    public int getKilled(){
        return killed;
    }

    //Set the count of how many piece the current piece had killed
    public void setKilled(int k){
        this.killed = k;
    }

    //Get the distance the piece had done
    public int getDistance() {
        return distance;
    }

    //Increase the distance the piece had done
    public void increaseDistance(int distance) {
        this.distance = this.distance + distance;
    }
}
