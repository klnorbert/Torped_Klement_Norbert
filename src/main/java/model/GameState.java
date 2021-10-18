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

    private PlayerVO Player1;
    private PlayerVO Player2;
    private boolean Turn;
    private boolean shouldExit;

    /**
     * @param player1    Player1 Data
     * @param player2    Player2 Data
     * @param turn       {@code true} 1Player Turn, {@code false} 2Player Turn
     * @param shouldExit exit the program
     */
    public GameState(PlayerVO player1, PlayerVO player2, boolean turn, boolean shouldExit) {
        Player1 = player1;
        Player2 = player2;
        Turn = turn;
        this.shouldExit = shouldExit;
    }

    //Setter

    public void setTurn(boolean turn) {
        Turn = turn;
    }

    public void setPlayer1(PlayerVO player1) {
        Player1 = player1;
    }

    public void setPlayer2(PlayerVO player2) {
        Player2 = player2;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    //Getter

    public boolean isTurn() {
        return Turn;
    }

    public PlayerVO getPlayer1() {
        return Player1;
    }

    public PlayerVO getPlayer2() {
        return Player2;
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    /**
     * Override Normal toString
     *
     * @return every data on this class
     */
    @Override
    public String toString() {
        return "GameState{" +
                ", Player1=" + Player1 +
                ", Player2=" + Player2 +
                ", shouldExit=" + shouldExit +
                '}';
    }

    /**
     * Builder for {@link GameState}.
     */
    public static final class GameStateBuilder {
        private PlayerVO Player1;
        private PlayerVO Player2;
        private boolean Turn;
        private boolean shouldExit;

        private GameStateBuilder() {
        }

        public static GameStateBuilder builder() {
            return new GameStateBuilder();
        }

        public void setTurn(boolean turn) {
            Turn = turn;
        }

        public boolean isTurn() {
            return Turn;
        }


        //Setter

        public void setPlayer1(PlayerVO player1) {
            Player1 = player1;
        }

        public void setPlayer2(PlayerVO player2) {
            Player2 = player2;
        }

        public void setShouldExit(boolean shouldExit) {
            this.shouldExit = shouldExit;
        }


        //Getter

        public PlayerVO getPlayer1() {
            return Player1;
        }

        public PlayerVO getPlayer2() {
            return Player2;
        }

        public boolean isShouldExit() {
            return shouldExit;
        }

        public GameStateBuilder withPlayer1Map(PlayerVO Player1) {
            this.Player1 = Player1;
            return this;
        }

        public GameStateBuilder withPlayer2Map(PlayerVO Player2) {
            this.Player2 = Player2;
            return this;
        }

        public GameStateBuilder withShouldExit(boolean shouldExit) {
            this.shouldExit = shouldExit;
            return this;
        }

        public GameState build() {
            return new GameState(Player1, Player2, Turn, shouldExit);
        }
    }
}
