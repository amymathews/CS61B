package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

import static gitlet.Utils.*;

public class StagingArea implements Serializable {

    // we need three things here, potential adds, potential removes and tracked files.

    private  HashMap <String, String> potential_adds;
    private HashSet<String> potential_removes;
    private  HashMap <String, String> tracked_files;

    public StagingArea() {
        potential_adds = new HashMap<>();
        potential_removes = new HashSet<>();
        tracked_files = new HashMap<>();
    }

    public boolean add (File file) {
        // create the blob
        Blob blob = new Blob(file);
        String blobId = blob.getCode();

        String filePath = file.getPath();
        // check if it's already been tracked
        String trackedId = tracked_files.get(filePath);

        if (trackedId != null) {
            // this file has been tracked now
            if (trackedId.equals(blobId)) {
                // the file is the same as the tracked one now
                // the file could be in addMap or removeSet, only one is possible
                if (potential_adds.remove(filePath) != null) {
                    // the file was in addMap, now it's removed
                    return true;
                }
                else {
                    return potential_removes.remove(filePath);
                }
            }
        }
        String prevId = potential_adds.put(filePath, blobId);
        if (prevId != null && prevId.equals(blobId)) {
            // added the same blob before
            return false;
        }
        if (!blob.getFile().exists()) {
            // redundant ?
            blob.save();
        }
        return true;

    }


    /** add the files  **/
    public HashMap<String, String> commit() {
        tracked_files.putAll(potential_adds);
        for (String filePath : potential_removes) {
            tracked_files.remove(filePath);
        }
        return tracked_files;  // Why return the trackedMap?  Because when we commit, we need a new trackedMap
    }

    /** Helper Functions. **/
    public void save(){

        writeObject(Driver.STAGING_AREA, this);
    }
    /** read from file. **/

    public static StagingArea fromFile() {

        return readObject(Driver.STAGING_AREA, StagingArea.class);
    }


}




