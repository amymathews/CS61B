package gitlet;
import java.io.File;

/** Driver class for Gitlet, the tiny stupid version-control system.
 *  @author Amy Mathews
 *  NOTE: actaully worked with a bunch of random people for different OHs and Labs, TAs really helped too.
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) {
        // FILL THIS IN
//            setupPersistence();
            switch (args[0]) {
                case "init":
                    Driver.init();
                    break;
                case "add":
                    Driver.add(args[1]);
                    break;
                case "commit":
                    String message = args[1];
                    Driver.commit(message);
                    break;
                case "status":
                    Driver.status();
                    break;
                case "checkout":
                    // case where
                    if( args.length == 3) {
                        Driver.checkout();
                    }
                    if( args.length == 4 ){
//                        String fileName = args[3];
                        String commitId = args[1];
                        Driver.checkout(commitId);
                    }
                    break;
                case "log":
                    Driver.log();
                default:

            }
            return;
        }
    }



