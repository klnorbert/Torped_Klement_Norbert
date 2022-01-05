package torpedo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <h1>Torpedo Project.</h1>
 * This is the torpedo.Main Method.
 *
 * @author Klement Norbert
 */
class Main {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("torpedo");
    }
}