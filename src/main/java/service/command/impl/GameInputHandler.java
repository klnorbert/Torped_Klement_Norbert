package service.command.impl;

import service.command.Command;

import java.util.List;

/**
 * Component that handles a given input.
 *
 * @author Klement Norbert
 */
public class GameInputHandler {

     //Final!
    private final List<Command> commandList;

    /**
     *  Constructor
     * @param commandList Arraylist contains all commands
     */
    public GameInputHandler(List<Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * Handles an input through a list of {@link Command}s.
     *
     * Only the first applicable command will be run.
     *
     * @param input the input as a string to be handled
     */
    public void handleInput(String input) {
        for (Command command : commandList) {
            if (command.canProcess(input)) {
                command.process(input);
                break;
            }
        }
    }

}
