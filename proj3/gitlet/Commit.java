package gitlet;

import java.io.Serializable;
import java.io.File;
import static gitlet.Utils.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Commit implements Serializable {

    private final Date date;
//    private final String git_log;
    String commit_code;
    String branch;
    String parent1;
    String parent2;
    String commit_message;
    private HashMap<String, String> blob_tracker; // <fileName, SHA1>
    private final List<String> parents;
    private final File commit_file;


    /** Constructor **/
    Commit(String _commit_message,List<String> parents, HashMap<String, String> blob_tracker ) {

        this.commit_message = _commit_message;
        this.parents = parents;
        this.blob_tracker = blob_tracker;
        this.date = new Date();
        this.commit_code = getCode();
        this.commit_file = join(Driver.COMMITS_FOLDER, commit_code);
    }

    /** first commit**/
    public Commit(){
        this.commit_message = "initial commit";
        this.parents = new ArrayList<>();
        this.blob_tracker = new HashMap<>();
        this.date = new Date(0); // The timestamp for this initial commit will be 00:00:00 UTC, Thursday, 1 January 1970
        this.commit_code = getCode();
        this.commit_file = Utils.join(Driver.COMMITS_FOLDER, this.commit_code);

    }
    /** get the commit object from a particular commit's sha1 id */
    public static Commit fromFile(String commit_id) {
        File file = Utils.join(Driver.COMMITS_FOLDER, commit_id);
        return Utils.readObject(file, Commit.class);
    }


    /** Helper Functions**/

    /** We generate the id using metadata required in each commit.
     * Returns the SHA-1 hash of the concatenation of the strings **/
    public String getCode(){

        return sha1(this.commit_message, getTimestamp(), this.parents.toString(), this.blob_tracker.toString());

    }

    public String getTimestamp() {
        // Thu Jan 1 00:00:00 1970 +0000
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public void save() {
        Utils.writeObject(commit_file, this);
    }
}
