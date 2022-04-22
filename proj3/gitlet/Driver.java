package gitlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import static gitlet.Utils.*;

public class Driver {

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_FOLDER = join(CWD, ".gitlet");
    /** The .gitlet/commits folder. Store blobs commits. */
    public static final File COMMITS_FOLDER = join(GITLET_FOLDER, "commits");
    /** The .gitlet/commits_folder/commit.txt. Store commit metadata. **/
    public static final File COMMITS = join(COMMITS_FOLDER, "commits.txt");
    /** The .gitlet/blobs folder. Stores Blob contents. **/
    public static final File BLOB_FOLDER = join(GITLET_FOLDER, "Blobs");
    /** The .gitlet/blobs/blobs.txt file holding all the blob contents. **/
    public static final File BLOBS = join(BLOB_FOLDER, "blobs.txt");
    /** The .gitlet/staging area folder holds two folders within it.  */
    public static final File STAGING_AREA_FOLDER = join(GITLET_FOLDER, "staging area");
    /** file for staging area. **/
    public static final File STAGING_AREA = join(STAGING_AREA_FOLDER,"staging.txt");
    /** The .gitlet/staging_area/potential_add folder. Holds all the potential add blobs**/
    public static final File POTENTIAL_ADD = join(STAGING_AREA_FOLDER, "potential add in staging area area");
    /** the .gitlet/staging_area/potential_add/add.txt. Holds the blobs that could potentially be added to the blob.txt.**/
    public static final File ADD = join(POTENTIAL_ADD, "add.txt");
    /** The .gitlet/staging_area/potential_remove folder. Holds all the potential remove blobs**/
    public static final File POTENTIAL_REMOVE = join(STAGING_AREA_FOLDER, "potential remove in staging area area");
    /** the .gitlet/staging_area/potential_add/remove.txt. Holds the blobs that could potentially be removed from the blob.txt.**/
    public static final File REMOVE = join(POTENTIAL_REMOVE, "remove.txt");
    /** pointer to the head. **/
    public static final File HEAD = join(GITLET_FOLDER, "HEAD.txt");
    /** the .gitlet/branches folder. Holds all the branches. **/
    public static final File BRANCHES_FOLDER = join(GITLET_FOLDER, "branches");
//    public static StagingArea stagingArea = STAGING_AREA.exists() ? StagingArea.fromFile() : new StagingArea();


    /** init method responsible for starting the version control system in the directory its in.
     * Kind of like the SetUp Persistance  for lab12  **/
    public static void init(){
//        File gitlet = new File(".gitlet");
        if (GITLET_FOLDER.exists()) {
            System.out.println("A gitlet version-control system exists in this current directory.");
        }
        else {
            GITLET_FOLDER.mkdir();
            COMMITS_FOLDER.mkdir();
            BLOB_FOLDER.mkdir();
            STAGING_AREA_FOLDER.mkdir();
            POTENTIAL_ADD.mkdir();
            POTENTIAL_REMOVE.mkdir();
            BRANCHES_FOLDER.mkdir();
            try {
                COMMITS.createNewFile();
                BLOBS.createNewFile();
                ADD.createNewFile();
                REMOVE.createNewFile();
                HEAD.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
            // do the initial commit, then write the objects in the file.
            Commit first_commit = new Commit();
            first_commit.save();
            // get the first_commit id so we can update the head.
            String first_commit_code = first_commit.getCode();
            // update the head.
//            Branch.setupPointer(HEAD, first_commit_code);
            if (!HEAD.exists()) {
                try {
                    HEAD.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Utils.writeContents(HEAD, first_commit_code);
            // save the commit to master.
            Branch master  = new Branch(first_commit);
            master.save();

            // we now need to create a staging area.
            StagingArea initial_staging_area = new StagingArea();
            initial_staging_area.save();

        }

    }

    /** this is kinda like writestory in lab 12
     * create new code for blob content
     * 1) check if the file exists
     * 2) if not, add to staging area **/

    public static void add(String name) {

        File fileobj = join(CWD, name);

        if (!fileobj.exists()) {
            System.out.println("This file does not exist! ");
            System.exit(0);
        }
        // this mean the files exists now:
        StagingArea obj = StagingArea.fromFile();

        boolean addable = obj.add(fileobj);
        if(addable){
            obj.save();
        }
    }

    public static void commit(String message){
        StagingArea obj = StagingArea.fromFile();

        Map<String, String> newTrackedMap = obj.commit();
        // save the stagingArea since commit will change it
        obj.save();
        List<String> parents = new ArrayList<>();
        Commit headCommit = getHEADCommit();

         parents.add(headCommit.getCode());

         Commit commit = new Commit(message, parents, newTrackedMap);
         commit.save();
         // set HEAD pointer
        writeContents(HEAD, commit.getCode());

    }
    public static void status(){

    }
    public static void log(){

    }

    public static void checkout(String name) {
        // look where head is pointing,
        //check if that file exits in cwd
        // if it does exist
        // // check if contents different -> overwrite
        //else return the same thing.
        //if doesn't -> errors.
        String filePath = getFile(name).getPath();
        if (!getHEADCommit().restoreTrackedFile(filePath)) {
            System.out.println("File does not exist in that commit.");
        }

    }





    /** Helper Functions **/

    public static String  getCode(File filename){
        String filePath = filename.getPath();
        byte[] contents = Utils.readContents(filename);
        return sha1(filePath, contents);
    }

    /** Given file name, return the full File Object */
    public static File getFile(String name) {
        if (Paths.get(name).isAbsolute()) {
            return new File(name);
        } else {
            return join(CWD, name);
        }
    }
    public static Commit getHEADCommit() {
        String head_code = readContentsAsString(HEAD);
        return Commit.fromFile(head_code);
    }

}
