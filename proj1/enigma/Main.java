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
        String msg = "";
        int counter = 0;
//        for(int i = 0; i < _numRotors; i += 1 ){
//            if(){
//                counter += 1;
//            }
//        }
        if (_setting.contains("*")) {
            if(counter > _numPawls){
                throw new EnigmaException("Too many moving rotors");
            }
            else {
                setUp(obj, _setting);
            }
        } else {
            throw error("Wrong setting!");
        }
        while (_input.hasNextLine()) {
            _setting = _input.nextLine();
            if (_setting.contains("*")) {
                setUp(obj, _setting);
            }
            else if( _setting.equals("")){
                _output.println();
            }
            else {
                msg = obj.convert(_setting.replaceAll(" ", ""));
                printMessageLine(msg);
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
            _alphabet = new Alphabet(text);
            int numRotors = _config.nextInt();
            int numPawls = _config.nextInt();
            allRotors = new ArrayList<>();
            if (text.contains("(") | text.contains(")") | text.contains("*")) {
                throw new EnigmaException("Wrong configuration format!");
            }
            _alphabet = new Alphabet(text);
            if(_config.hasNextInt()){
                throw new EnigmaException(" Wrong configuration format!");
            }

            while(_config.hasNext()){
                allRotors.add(readRotor());
            }
            this._numPawls = numPawls;
            this._numRotors = numRotors;

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
            String name = (_config.next());
            String rotortype = _config.next();
            /** if the rotortype has something in it, we want to add it to the cycle **/
            while(_config.hasNext("\\(.*\\)")){
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
        String[] rotor_list = new String[M.numRotors()];
        String cycles = "";
        String[] setA = settings.split(" ");
        String settingInput = setA[setA.length-1];

        if(!setA[0].equals("*")){
            throw new EnigmaException("Settings must start with *!");
        }
        int i = 0;
        if(setA.length - 2 < M.numRotors()){
            throw new EnigmaException("Too few arguments");
        }
        info.next();
        while (i < M.numRotors()) {
            rotor_list[i] = info.next();
            i += 1;
        }

        if(settingInput.length() != M.numRotors()-1){
            throw new EnigmaException("wrong setting input, length is not correct.");
        }
        for(int s = 0; s < settingInput.length(); s += 1 ){
            if(!_alphabet.contains(settingInput.charAt(s))){
                throw new EnigmaException("wrong setting input, letter not present.");
            }
        }
        /** check if there is a repeating error **/
        for(int j = 0; j < rotor_list.length-1; j += 1) {
            for (int k = j + 1; k < rotor_list.length; k += 1) {
                if (rotor_list[j].equals(rotor_list[k])) {
                    throw new EnigmaException("rotor is repeated!");
                }
            }
        }
        /** check if the first rotor is fixed**/

        Permutation perm = new Permutation(cycles, _alphabet);
        M.setPlugboard(perm);
        M.insertRotors(rotor_list);
        M.setRotors(settingInput);

        // FIXME
    }

    /** Return true iff verbose option specified. */
    static boolean verbose() {
        return _verbose;
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private void printMessageLine(String msg) {
//        int counter = 0;
        for(int i = 0; i < msg.length(); i += 5) {
            if(i + 5 < msg.length()) {
                _output.print(msg.substring(i, i + 5) + " ");
            }
            else {
                _output.println(msg.substring(i));
            }
//            _output.println(msg.substring(i));
        }
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

    private int _numRotors;
    private int _numPawls;

    private ArrayList allRotors = new ArrayList<>();
}
