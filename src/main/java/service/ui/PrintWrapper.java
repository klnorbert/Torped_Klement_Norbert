package service.ui;

/**
 * Util class used to wrap operations that print to stdout.
 * Created for making unit testing easier.
 *
 * @author Klement Norbert
 */
public class PrintWrapper {

    /**
     * Prints a line to stdout.
     *
     * @param string the string to print :D
     */
    public void printLine(String string) {
        System.out.println(string);
    }

}
