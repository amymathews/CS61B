package enigma;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import java.util.*;

import ucb.util.CommandArgs;

import static enigma.EnigmaException.*;

/** Enigma simulator.
 *  @author Amy Mathews
 */
public final class Main {
    /** parese in and conf files
     * buidl machine according to specifiaction,
     * encrypt provided message**/

    /** Process a sequence of encryptions and decryptions, as
     *  specified by ARGS, where 1 <= ARGS.length <= 3.
     *  ARGS[0] is the name of a configuration file.
     *  ARGS[1] is optional; when present, it names an input file
     *  containing messages.  Otherwise, input comes from the standard
     *  input.  ARGS[2] is optional; when present, it names an output
     *  file for processed messages.  Otherwise, output goes to the
     *  standard output. Exits normally if there are no errors in the input;
     *  otherwise with code 1. */
    public static void main(String... args) {
        try {
            CommandArgs options =
                new CommandArgs("--verbose --=(.*){1,3}", args);
            if (!options.ok()) {
                throw error("Usage: java enigma.Main [--verbose] "
                            + "[INPUT [OUTPUT]]");
            }

            _verbose = options.contains("--verbose");
            new Main(options.get("--")).process();
            return;
        } catch (EnigmaException excp) {
            System.err.printf("Error: %s%n", excp.getMessage());
        }
        System.exit(1);
    }

    /** Open the necessary files for non-option arguments ARGS (see comment
      *  on main). */
    Main(List<String> args) {
        _config = getInput(args.get(0));

        if (args.size() > 1) {
            _input = getInput(args.get(1));
        } else {
            _input = new Scanner(System.in);
        }

        if (args.size() > 2) {
            _output = getOutput(args.get(2));
        } else {
            _output = System.out;
        }
    }

    /** Return a Scanner reading from the file named NAME. */
    private Scanner getInput(String name) {
        try {
            return new Scanner(new File(name));
        } catch (IOException excp) {
            throw error("could not open %s", name);
        }
    }

    /** Return a PrintStream writing to the file named NAME. */
    private PrintStream getOutput(String name) {
        try {
            return new PrintStream(new File(name));
        } catch (IOException excp) {
            throw error("could not open %s", name);
        }
    }

    /** Configure an Enigma machine from the contents of configuration
     *  file _config and apply it to the messages in _input, sending the
     *  results to _output.
     *  hub of machine
     *  call other methods here
     *  encrypt message in process
     *  encrypt and output*/
    private void process() {
        Machine obj = readConfig();
        String _setting = _input.nextLine();
        while (_input.hasNextLine()) {
            if (_setting.contains("*")) {
                setUp(obj, _setting);
            }
            else {
                throw new EnigmaException("Wrong format!");
            }

        }

        // FIXME
    }

    /** Return an Enigma machine configured from the contents of configuration
     *  file _config.
     *  parse through the _config file*/
    private Machine readConfig() {
        try {
            // FIXME
            String text = _config.next();
            _alphabet = new Alphabet();
            int numRotors = _config.nextInt();
            int numPawls = _config.nextInt();
            allRotors = new ArrayList<>();
            if (text.contains("(") | text.contains(")") | text.contains("*")) {
                throw new EnigmaException("Wrong configuration format!");
            }
            while(_config.hasNext()){
                allRotors.add(readRotor());
            }
            return new Machine(_alphabet, numRotors, numPawls, allRotors);
        } catch (NoSuchElementException excp) {
            throw error("configuration file truncated");
        }
    }

    /** Return a rotor, reading its description from _config.
     * helper function to readconfig */
    private Rotor readRotor() {
        try {
            // FIXME
            String cycles = "";
            String rotortype = (_config.next()).toUpperCase();
            String name = rotortype;
            String notches = rotortype.substring(1);
            /** if the rotortype has something in it, we want to add it to the cycle **/
            while(_config.hasNext("\\(.*\\)")){
                cycles += _config.next();
            }
            Permutation permutation =  new Permutation(cycles,_alphabet);

                if (name.charAt(0) == 'R') {
                    return new Reflector(name, permutation);
                } else if (name.charAt(0) == 'N') {
                    return new FixedRotor(name, permutation);
                } else if (name.charAt(0) == 'M') {
                    return new MovingRotor(name, permutation, notches);
                } else {
                    throw new EnigmaException("No valid rotor found!");
                }

        } catch (NoSuchElementException excp) {
            throw error("bad rotor description");
        }
    }

    /** Set M according to the specification given on SETTINGS,
     *  which must have the format specified in the assignment.
     *  parse the in file.*/
    private void setUp(Machine M, String settings) {
        M.setRotors(settings);
        // FIXME
    }

    /** Return true iff verbose option specified. */
    static boolean verbose() {
        return _verbose;
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private void printMessageLine(String msg) {

        // FIXME
    }

    /** Alphabet used in this machine. */
    private Alphabet _alphabet;

    /** Source of input messages. */
    private Scanner _input;

    /** Source of machine configuration. */
    private Scanner _config;

    /** File for encoded/decoded messages. */
    private PrintStream _output;

    /** True if --verbose specified. */
    private static boolean _verbose;

    private ArrayList allRotors;
}
