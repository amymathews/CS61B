package enigma;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;

import ucb.util.CommandArgs;

import static enigma.EnigmaException.*;

/** Enigma simulator.
 *  @author Amy Mathews
 */
public final class Main {

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
     *  results to _output. **/
    private void process() {
        Machine obj = readConfig();
        String setting = _input.nextLine();
        String msg = "";
        int counter = 0;

        if (setting.contains("*")) {
            if (counter > _numPawls) {
                throw new EnigmaException("Too many moving rotors");
            } else {
                setUp(obj, setting);
            }
        } else {
            throw error("Wrong setting!");
        }
        while (_input.hasNextLine()) {
            setting = _input.nextLine();
            if (setting.contains("*")) {
                setUp(obj, setting);
            } else if (setting.equals("")) {
                _output.println();
            } else {
                msg = obj.convert(setting.replaceAll(" ", ""));
                printMessageLine(msg);
            }
        }
    }

    /** Return an Enigma machine configured from the contents of configuration
     *  file _config. **/
    private Machine readConfig() {
        try {
            String text = _config.next();
            _alphabet = new Alphabet(text);
            int numRotors = _config.nextInt();
            int numPawls = _config.nextInt();
            int counter = 0;
            allRotors = new ArrayList<>();
            if (text.contains("(") | text.contains(")") | text.contains("*")
                    | text.contains("/d")) {
                throw new EnigmaException("Wrong configuration format!");
            }
            if (_config.hasNextInt()) {
                throw new EnigmaException(" Wrong configuration format!");
            }

            while (_config.hasNext()) {
                allRotors.add(readRotor());
            }

            this._numPawls = numPawls;
            this._numRotors = numRotors;

            return new Machine(_alphabet, numRotors, numPawls, allRotors);
        } catch (NoSuchElementException excp) {
            throw error("configuration file truncated");
        }
    }

    /** Return a rotor, reading its description from _config. **/
    private Rotor readRotor() {
        try {

            String cycles = "";
            String name = _config.next();
            String rotortype = _config.next();

            while (_config.hasNext("\\(.*\\)")) {
                cycles += _config.next();
            }

            Permutation permutation =  new Permutation(cycles, _alphabet);
            if (rotortype.charAt(0) == 'M') {
                String notches = rotortype.substring(1);
                return new MovingRotor(name, permutation, notches);
            } else if (rotortype.charAt(0) == 'R') {
                return new Reflector(name, permutation);
            } else if (rotortype.charAt(0) == 'N') {
                return new FixedRotor(name, permutation);
            } else {
                throw new EnigmaException("No valid rotor found!");
            }

        } catch (NoSuchElementException excp) {
            throw error("bad rotor description");
        }
    }

    /** Set M according to the specification given on SETTINGS,
     *  which must have the format specified in the assignment.
     *  parse the in file.
     *  set up the Machine object that you created
     *  in readConfig() as specified by the settings line in the .in file*/
    private void setUp(Machine M, String settings) {
        Scanner info = new Scanner(settings);
        String[] rotorList = new String[M.numRotors()];
        String cycles = "";
        String[] setA = settings.split(" ");

        if (!setA[0].equals("*")) {
            throw new EnigmaException("Settings must start with *!");
        }

        if (M.numRotors() + 2 > setA.length) {
            throw new EnigmaException("wrong number of rotors");
        }
        String settingInput = setA[_numRotors + 1];
        if (settingInput.length() > rotorList.length) {
            throw new EnigmaException(" invalid setting passed");
        }
        if (setA.length - 1 < M.numRotors()) {
            throw new EnigmaException("Too few arguments");
        }
        int i = 0;
        info.next();
        while (i < M.numRotors()) {
            rotorList[i] = info.next();
            i += 1;
        }

        if (settingInput.length() > rotorList.length) {
            throw new EnigmaException(" invalid setting passed");
        }

        if (settingInput.length() != M.numRotors() - 1) {
            throw new EnigmaException("wrong setting input, "
                    + "length is not correct.");
        }
        for (int s = 0; s < settingInput.length(); s += 1) {
            if (!_alphabet.contains(settingInput.charAt(s))) {
                throw new EnigmaException("wrong setting input, "
                        + "letter not present.");
            }
        }
        for (int j = 0; j < rotorList.length - 1; j += 1) {
            for (int k = j + 1; k < rotorList.length; k += 1) {
                if (rotorList[j].equals(rotorList[k])) {
                    throw new EnigmaException("rotor is repeated!");
                }
            }
        }

        for (int l = _numRotors + 2; l < setA.length; l += 1) {
            cycles += setA[l];
        }
        Permutation perm = new Permutation(cycles, _alphabet);
        M.setPlugboard(perm);
        M.insertRotors(rotorList);
        M.setRotors(settingInput);

    }

    /** Return true iff verbose option specified. */
    static boolean verbose() {
        return _verbose;
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private void printMessageLine(String msg) {
        for (int i = 0; i < msg.length(); i += 5) {
            if (i + 5 < msg.length()) {
                _output.print(msg.substring(i, i + 5) + " ");
            } else {
                _output.println(msg.substring(i));
            }
        }
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
    /** number of rotors. **/
    private int _numRotors;
    /** number of pawls. **/
    private int _numPawls;
    /** all available rotors. **/
    private ArrayList allRotors = new ArrayList<>();

}
