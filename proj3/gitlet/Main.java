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
                    Driver.init();
                    break;
                case "add":
                    Driver.add(args[0]);
                    break;
                case "commit":
                    Driver.commit();
                    break;
                case "status":
                    Driver.status();
                    break;
                case "checkout":
                    Driver.checkout();
                    break;
                case "log":
                    Driver.log();
                default:

            }
            return;
        }
    public static void setupPersistence() {
        // FIXME
    }
    }


