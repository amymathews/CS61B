package gitlet;
import java.io.File;
import java.io.IOException;

/** Driver class for Gitlet, the tiny stupid version-control system.
 *  @author Amy Mathews
 *  NOTE: actaully worked with a bunch of random people for different OHs and Labs, TAs really helped too.
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) throws IOException {
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
                    if(message.equals("")){
                        System.out.println("Please enter a commit message.");
                        System.exit(0);

                    }
                    Driver.commit(message);
                    break;
                case "status":
                    Driver.status();
                    break;
                case "checkout":
                    // case where
                    if (args.length == 3) {
                        Driver.checkout();
                    }
                    if (args.length == 4) {
//                        String fileName = args[3];
                        String commitId = args[1];
                        Driver.checkout(commitId);
                    }
                    break;
                case "log":
                    Driver.log();
                    break;
                case "global-log":
                    Driver.global_log();
                case "rm":
//                    Driver.remove(args[1]);
                    break;
                default:
                    System.out.println("No command with that name exists.");

            }
            // check nonsense command.
            return;
        }
    }



