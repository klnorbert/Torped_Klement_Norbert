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
public class DefaultCommand implements Command {
    /**
     * FINAL!
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCommand.class);
    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";
    private final PrintWrapper printWrapper;
    /**
     * Constructor
     * @param printWrapper Print the error message
     */
    public DefaultCommand(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    /**
     * @param input stupidity
     * @return {@code true} always, {@code false} NANI?
     */
    @Override
    public boolean canProcess(String input) {
        return true;
    }

    /**
     * Print "Unknown command"
     * @param input stupidity
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing default command");
        printWrapper.printLine(UNKNOWN_COMMAND_MESSAGE);
    }

}
