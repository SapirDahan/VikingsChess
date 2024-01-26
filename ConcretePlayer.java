public class ConcretePlayer implements Player{

    private boolean isPlayerOne;
    private int wins = 0;

    //Constructor
    public ConcretePlayer(boolean isPlayerOne) {
        this.isPlayerOne = isPlayerOne;
    }

    //Return if the player is player 1 (Defender)
    @Override
    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    //Return how many wins the player have
    @Override
    public int getWins() {
        return wins;
    }

    //Increase a win for the current player
    public void anotherWin(){
        wins++;
    }
}
