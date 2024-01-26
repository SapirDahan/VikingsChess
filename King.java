public class King extends ConcretePiece{

    private Player owner;
    private String type = "♔";

    //Constructor
    public King(Player owner) {
        super();
        super.setType(this.type);
        this.owner = owner;
    }

    //Get the owner of the king (suppose to be always player 1)
    public Player getOwner(){
        return owner;
    }

    //Return the type of the king
    public String getType(){
        return type;
    }

}
