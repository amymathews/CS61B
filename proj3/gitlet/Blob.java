package gitlet;

import java.io.Serializable;
import static gitlet.Utils.*;
import java.io.File;
import java.nio.file.Paths;


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
        this.blob_file = getFile(this.source_code);
    }




    /** Helper functions. **/

    public String getCode(){
        return source_code;
    }

    /** Given file name, return the full File Object */
    public static File getFile(String name) {
        if (Paths.get(name).isAbsolute()) {
            return new File(name);
        } else {
            return Utils.join(Driver.CWD, name);
        }

    }
    public void save() {

    }



}
