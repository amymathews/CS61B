package gitlet;

import java.io.Serializable;
import java.io.File;
import static gitlet.Utils.*;



public class Branch implements Serializable {

    private String commit_code;
    private Commit commit_head;
    private String branch;


    // initial Branch constructor.
    public Branch(Commit first_commit) {

        this.commit_head = first_commit;
        this.commit_code = commit_head.getCode();
        this.branch = "master";


    }
    // general constructor.
    public Branch(Commit head, String new_branch) {

        this.commit_head = head;
        this.commit_code = head.getCode();
        this.branch = new_branch;

    }

    /** Helper Functions. **/

    public void save() {
        File file = join(Driver.BRANCHES_FOLDER, this.branch);
        writeObject(file, this);
    }





}
