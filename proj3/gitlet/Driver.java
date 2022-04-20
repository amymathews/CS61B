package gitlet;
import java.util.*;
import java.io.File;

public class Driver {

    public void init(){
        File gitlet = new File(".gitlet");
        if (gitlet.exists()) {
            System.out.println("A gitlet version-control system exists in this current directory.");
        }

    }

    public void add(){

    }
    public void status(){

    }
    public void log(){

    }

    public void checkout(){

    }
}
