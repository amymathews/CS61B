package capers;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;

public class Example implements Serializable {
    public static void main(String[] args) throws IOException {
//        System.out.println(System.getProperty("user.dir"));
        File exampleFile = new File("example.txt");
        exampleFile.createNewFile();
        File exampleDir = new File("exampleDir");
        exampleDir.mkdir();
        Utils.writeContents(exampleFile, "Hey");
        System.out.println(Utils.readContentsAsString(exampleFile));
//        HashSet<String> set = new HashSet<>();
//        set.add("Michelle");
//        set.add("Omar");
//        set.add("Zoe");
//        set.add("Arjun");
        File f = new File("set");
//        Utils.writeObject(f,set);
        HashSet<String> setLoaded = Utils.readObject(f,HashSet.class);
        System.out.println(setLoaded);
        Utils.join(exampleFile,"heylo");

    }
}
