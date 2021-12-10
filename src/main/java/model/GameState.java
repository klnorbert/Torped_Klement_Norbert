package model;

/**
 * Represents the current state of the game.
 *
 * @author Klement Norbert
 */
public class GameState {
    public static GameStateBuilder builder() {
        return new GameStateBuilder();
    }

    private PlayerVO player1;
    private PlayerVO player2;
    private boolean turn;
    private boolean shouldExit;
    private boolean empty;
    private int shipFragment;
    private boolean shipPlace;
    private int shipFootage;


    /**
     * Constructor.
     *
     * @param player1    Player1 Data
     * @param player2    Player2 Data
     * @param empty player1 and player2 data is empty
     */
    public GameState(PlayerVO player1, PlayerVO player2, boolean empty) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = true;
        this.shouldExit = false;
        this.shipFragment = 0;
        this.shipPlace = false;
        this.empty = empty;
        this.shipFootage = 0;
    }

    //Setter


    public void setShipFootage(int shipFootage) {
        this.shipFootage = shipFootage;
    }

    public void setShipPlace(boolean shipPlace) {
        this.shipPlace = shipPlace;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void setPlayer1(PlayerVO player1) {
        this.player1 = player1;
    }

    public void setPlayer2(PlayerVO player2) {
        this.player2 = player2;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    public void setShipFragment(int shipFragment) {
        this.shipFragment = shipFragment;
    }
    //Getter


    public int getShipFootage() {
        return shipFootage;
    }

    public boolean isShipPlace() {
        return shipPlace;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isTurn() {
        return turn;
    }

    public PlayerVO getPlayer1() {
        return player1;
    }

    public PlayerVO getPlayer2() {
        return player2;
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    public int getShipFragment() {
        return shipFragment;
    }

    /**
     * Override Normal toString.
     *
     * @return every data on this class
     */
    @Override
    public String toString() {
        return "GameState{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", turn=" + turn +
                ", shouldExit=" + shouldExit +
                ", empty=" + empty +
                ", shipFragment=" + shipFragment +
                ", shipPlace=" + shipPlace +
                ", shipFootage=" + shipFootage +
                '}';
    }

    /**
     * Builder for {@link GameState}.
     */
    public static final class GameStateBuilder {
        private PlayerVO player1;
        private PlayerVO player2;
        private boolean turn;
        private boolean shouldExit;
        private int shipFragment;
        private boolean empty;

        private GameStateBuilder() {
        }

        public static GameStateBuilder builder() {
            return new GameStateBuilder();
        }


        //Setter

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public void setTurn(boolean turn) {
            this.turn = turn;
        }

        public void setPlayer1(PlayerVO player1) {
            this.player1 = player1;
        }

        public void setPlayer2(PlayerVO player2) {
            this.player2 = player2;
        }

        public void setShouldExit(boolean shouldExit) {
            this.shouldExit = shouldExit;
        }

        public void setShipFragment(int shipFragment) {
            this.shipFragment = shipFragment;
        }

        //Getter

        public boolean isEmpty() {
            return empty;
        }

        public boolean isTurn() {
            return turn;
        }

        public PlayerVO getPlayer1() {
            return player1;
        }

        public PlayerVO getPlayer2() {
            return player2;
        }

        public boolean isShouldExit() {
            return shouldExit;
        }

        public int getShipFragment() {
            return shipFragment;
        }

        public GameStateBuilder withPlayer1Map(PlayerVO player1) {
            this.player1 = player1;
            return this;
        }

        public GameStateBuilder withPlayer2Map(PlayerVO player2) {
            this.player2 = player2;
            return this;
        }

        public GameStateBuilder withShouldExit(boolean shouldExit) {
            this.shouldExit = shouldExit;
            return this;
        }

        public GameState build() {
            return new GameState(player1, player2, empty);
        }
    }
}
