import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

/** A set of String values.
 *  @author
 */
class ECHashStringSet implements StringSet {
    private LinkedList<String>[] buckets;
    private int ogBucketLength;

    public ECHashStringSet(){

        buckets = (LinkedList<String>[]) new LinkedList[5];
        // loop through to make sure each index in a bucket
        // is full of linked list.
        for (int i = 0; i < 5; i++) {
            buckets[i] = new LinkedList<>();
        }
        ogBucketLength = 0;
    }

    @Override
    public void put(String s) {
        // FIXME
        // reach the load limit we resize.Given in spec: resize the hash table
        // as soon as the load factor exceeds 5
        if((double) ogBucketLength / (double) buckets.length > 5){
            resize();
        }
        int index = whichBucket(s);
        while (index > buckets.length) {
            resize();
        }
        //get the linked list at buckets[index]
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        buckets[index].add(s);
        ogBucketLength += 1;

    }

    @Override
    public boolean contains(String s) {
        int index = whichBucket(s);
        return buckets[index].contains(s); // FIXME
    }

    @Override
    public List<String> asList() {
        LinkedList<String> retList = new LinkedList<>();
        for (LinkedList<String> l : buckets) {
            retList.addAll(l);
        }
        return retList; // FIXME
    }

    private int whichBucket(String s){
        //figure out which index i bucket to go to
        // get linked list
        int index = s.hashCode() % buckets.length;
        if (index < 0) {
            index = index & 0x7fffffff % buckets.length;
        }
        return index;
    }
    private void resize(){
        LinkedList<String>[] oldBucket = buckets;
        buckets = new LinkedList[2 * oldBucket.length];
        // we need to rewrite the size.
        ogBucketLength = 0;
        for (LinkedList<String> l : oldBucket) {
            if (l != null) {
                for (String s : l) {
                    this.put(s);
                }
            }
        }

    }

}

