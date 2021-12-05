package service.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import service.ui.PrintWrapper;

/**
 * A default command, which should be run when no other {@link Command}
 * implementations were able to process the input.
 *
 * @author Klement Norbert
 */
public class HelpCommand implements Command {

    //FINAL!

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCommand.class);
    private static final String HELP_COMMAND_MESSAGE = "exit: Close the App\n" +
            "print: Print the you map and your hits\n" +
            "hit [A-J] [1-9]: Launce your rocket and make terrorist go Kaboom\n" +
            "end turn: you hand over control to the other player";
    private static final String HELP_COMMAND = "help";
    private final PrintWrapper printWrapper;

    /**
     * Constructor
     *
     * @param printWrapper Print the error message
     */
    public HelpCommand(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    /**
     * @param input "help" as String
     * @return {@code true} always, {@code false} NANI?
     */
    @Override
    public boolean canProcess(String input) {
        return HELP_COMMAND.equals(input);
    }

    /**
     * Print "help command"
     *
     * @param input stupidity
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing help command");
        printWrapper.printLine(HELP_COMMAND_MESSAGE);
    }

}
