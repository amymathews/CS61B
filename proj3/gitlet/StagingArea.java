package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import static gitlet.Utils.*;

public class StagingArea implements Serializable {

    // we need three things here, potential adds, potential removes and tracked files.

    private  HashMap <String, String> potential_adds;
    private  HashMap <String, String> potential_removes;
    private  HashMap <String, String> tracked_files;

    public StagingArea() {
        potential_adds = new HashMap<>();
        potential_removes = new HashMap<>();
        tracked_files = new HashMap<>();
    }

    public boolean add(File file) {
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
                    return potential_removes.remove(filePath) == null;
                }
            }
        }
        return false;
    }

    /** Helper Functions. **/
    public void save(){
        writeObject(Driver.STAGING_AREA, this);
    }
    /** read from file. **/
//    public void read(){
//        return readObject(Driver.STAGING_AREA,this);
//    }
    public static StagingArea fromFile() {
        return Utils.readObject(Driver.STAGING_AREA, StagingArea.class);
    }


}




