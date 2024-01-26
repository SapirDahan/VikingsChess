public class Pawn extends ConcretePiece{

    private Player owner;

    //Constructor
    public Pawn(Player owner) {
        super();

        //If player 1 then he is ♙ and if he is player 2 then he is ♟
        if(owner.isPlayerOne()){
            super.setType("♙");
        }
        else{
            super.setType("♟");
        }

        this.owner = owner;
    }

    //Get the owner of the pawn
    public Player getOwner() {
        return owner;
    }

}
