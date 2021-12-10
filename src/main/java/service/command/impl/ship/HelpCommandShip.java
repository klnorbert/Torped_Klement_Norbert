package service.command.impl.ship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;
import service.ui.PrintWrapper;

/**
 * Write every command in Ship Phase. {@link Command}
 *
 * @author Klement Norbert
 */
public class HelpCommandShip implements Command {

    //Final!

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCommandShip.class);
    private static final String HELP_COMMAND_MESSAGE =
            "exit: Close the App\n" +
                    "print: Print the ship place map\n" +
                    "add [A-J] [1-9] [1-4]: Add a ship your fleet size length is locked by the other player\n" +
                    "end turn: you hand over control to the other player\n" +
                    "place [A-J] [1-9] [1-4] [1-5]: you hand over control to the other player\n" +
                    "end place: you end the ship place phase when you didn't place a ship this round\n";
    private static final String HELP_COMMAND = "help";
    private final PrintWrapper printWrapper;

    /**
     * Constructor.
     *
     * @param printWrapper Print the error message
     */
    public HelpCommandShip(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    /**
     * Command interface Override.
     *
     * @param input user writes something
     * @return {@code true} if the user wrote the following "help", {@code false} otherwise
     */
    @Override
    public boolean canProcess(String input) {
        return HELP_COMMAND.equals(input);
    }

    /**
     * Print every command with description.
     * <p>
     * Command interface Override.
     *
     * @param input "help" as String
     */
    @Override
    public void process(String input) {
        LOGGER.info("Performing help command");
        printWrapper.printLine(HELP_COMMAND_MESSAGE);
    }

}
