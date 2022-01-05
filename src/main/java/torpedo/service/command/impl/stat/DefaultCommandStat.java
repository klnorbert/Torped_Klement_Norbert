package torpedo.service.command.impl.stat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.service.command.Command;
import torpedo.service.ui.PrintWrapper;

/**
 * A default command, which should be run when no other {@link Command}
 * implementations were able to process the input.
 *
 * @author Klement Norbert
 */
public class DefaultCommandStat implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCommandStat.class);
    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";
    private final PrintWrapper printWrapper;

    /**
     * Constructor.
     *
     * @param printWrapper Print the error message
     */
    public DefaultCommandStat(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} always, {@code false} NANI?
     */
    @Override
    public boolean canProcess(String input) {
        return true;
    }

    /**
     * Print Unknown command.
     * <p>
     * Command interface Override.
     *
     * @param input something
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing default command");
        printWrapper.printLine(UNKNOWN_COMMAND_MESSAGE);
    }

}
