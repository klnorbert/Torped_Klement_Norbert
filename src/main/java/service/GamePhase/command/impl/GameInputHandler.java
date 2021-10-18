package service.GamePhase.command.impl;

import service.GamePhase.command.GameCommand;

import java.util.List;

/**
 * Component that handles a given input.
 *
 * @author Klement Norbert
 */
public class GameInputHandler {

     //Final!
    private final List<GameCommand> commandList;

    /**
     *  Constructor
     * @param commandList Arraylist contains all commands
     */
    public GameInputHandler(List<GameCommand> commandList) {
        this.commandList = commandList;
    }

    /**
     * Handles an input through a list of {@link GameCommand}s.
     *
     * Only the first applicable command will be run.
     *
     * @param input the input as a string to be handled
     */
    public void handleInput(String input) {
        for (GameCommand command : commandList) {
            if (command.canProcess(input)) {
                command.process(input);
                break;
            }
        }
    }

}
