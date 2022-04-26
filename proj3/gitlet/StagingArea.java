package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.TreeMap;

import static gitlet.Utils.*;

public class StagingArea implements Serializable {

    // we need three things here, potential adds, potential removes and tracked files.

    private  TreeMap <String, String> potential_adds;
    private HashSet<String> potential_removes;
    private TreeMap<String, String> tracked_files;

    public StagingArea() {
        potential_adds = new TreeMap<>();
        potential_removes = new HashSet<>();
        tracked_files = new TreeMap<>();
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
    public TreeMap<String, String> commit() {
        tracked_files.putAll(potential_adds);
        for (String filePath : potential_removes) {
            tracked_files.remove(filePath);
        }
        return tracked_files;  // need a new map every time we commit.
    }

    /** Helper Functions. **/
    public void save(){

        writeObject(Driver.STAGING_AREA, this);
    }
    /** read from file. **/
    public static StagingArea fromFile() {

        return readObject(Driver.STAGING_AREA, StagingArea.class);
    }

    public boolean remove(File file) {
        /**
         * What case return false?
         * 1. not tracked, not added
         * 2. tracked,  removed before
         *
         * TO DO: remove.add()  add.remove
         * */
        String filePath = file.getPath();

        String trackedId = tracked_files.get(filePath);

        String addedId = potential_adds.remove(filePath);

        if (trackedId != null) {
            // tracked
            return potential_removes.add(filePath);
        } else {
            // not tracked
            if (addedId == null) {
                // not added
                // case 1, not need to change removeSet
                return false;
            } else {
                potential_removes.add(filePath);
            }
        }
        return true;
    }

}




