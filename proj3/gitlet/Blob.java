package gitlet;

import java.io.Serializable;
import static gitlet.Utils.*;
import java.io.File;


public class Blob implements Serializable {
    /** We need four things here: **/
    /** The source file of the Blob, given by the constructor */
    private final File source;

    /** content of this blob */
    private final byte[] content;

    /** sha-1 ID it's only related to the src file and the contents, doesn't relate to where it's stored! */
    private final String source_code;

    /** The blob file stored in .gitlet **/
    private final File blob_file;

    public Blob(File source) {

        this.source = source;
        this.content = readContents(source);
        String filePath = source.getPath();
        this.source_code = sha1(filePath, this.content);
        this.blob_file = getObjectFile(this.source_code);
    }

    /** Helper functions. **/

    public String getCode(){

        return source_code;
    }

    /** Given file name, return the full File Object */
    public File getFile(){

        return blob_file;
    }
    public void save() {
        saveObjectFile(blob_file, this);
    }
    /** here we are reading the lob from the **/
    public static Blob fromFile(String id) {

        return readObject(getObjectFile(id), Blob.class);
    }
    public void writeContentToSource() {

        writeContents(source, content);
    }
    public static File getObjectFile(String id) {
        String dirName = id.substring(0, 2);    // id first 2 digits as  dir name
        String fileName = id.substring(2);
        return join(Driver.BLOB_FOLDER, dirName, fileName);
    }

    public static void saveObjectFile(File file, Serializable object) {
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        Utils.writeObject(file, object);
    }


}
