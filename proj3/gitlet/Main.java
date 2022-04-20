package gitlet;
import java.io.File;

/** Driver class for Gitlet, the tiny stupid version-control system.
 *  @author
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) {
        // FILL THIS IN
            if (args.length == 0) {

            }
            setupPersistence();
            switch (args[0]) {
                case "init":

                    break;
                case "add":

                    break;
                case "commit":

                    break;
                case "checkout":

                    break;
                case "log":

                default:

            }
            return;
        }
    public static void setupPersistence() {
        // FIXME
    }
    }


