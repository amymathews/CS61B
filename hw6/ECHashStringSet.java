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
        for (int i = 0; i < 5; i++) {
            buckets[i] = new LinkedList<String>();
        }
        ogBucketLength = 0;
    }

    @Override
    public void put(String s) {
        // FIXME
        // reach the load limit we resize.
        if(buckets.length != 0 && (double) ogBucketLength/ (double) buckets.length > 5){
            resize();
        }
        int index = whichBucket(s);
        while (index > buckets.length) {
            resize();
        }
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
        int index = s.hashCode() % buckets.length;
        if (index < 0) {
            index = index & 0x7fffffff % buckets.length;
        }
        return index;
    }
    private void resize(){
        LinkedList<String>[] oldBucket = buckets;
        buckets = new LinkedList[2 * oldBucket.length];
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

