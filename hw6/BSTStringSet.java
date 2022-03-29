import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Implementation of a BST based String Set.
 * @author
 */
public class BSTStringSet implements StringSet, Iterable<String> {
    /** Creates a new empty set. */
    public BSTStringSet() {
        _root = null;
    }

    @Override
    public void put(String s) {
        // FIXME: PART A
        _root = traverseBST(s, _root);
    }

    @Override
    public boolean contains(String s) {
        // FIXME: PART A
        Node node = traverseBST(s, _root);
        return node != null && node.s.equals(s);
    }

    @Override
    public List<String> asList() {
        // FIXME: PART A. MUST BE IN SORTED ORDER, ASCENDING
        ArrayList<String> retList = new ArrayList<>();
        for(String s: this){
            retList.add(s);
        }
        return retList;
    }

    /** Note to self: Everything on the left of the parent is SMALLER and everything to right is BIGGER
     * LEFT -> SMALLER
     * RIGHT -> BIGGER **
     * NOTE: compareTo is useful to use here.
     * The method returns 0 if the string is equal to the other string.
     * < 0 if the string is lexicographically less than the other string
     * > 0 if the string is lexicographically greater than the other string*/
    private Node traverseBST(String s, Node n ){
        if (n == null) {
            return new Node(s);
        }
        int val = s.compareTo(n.s);
        if(val < 0){
            n.left = traverseBST(s, n.left);
        }
        else if(val > 0){
            n. right = traverseBST(s, n.right);
        }
        return  n;
    }

    /** Represents a single Node of the tree. */
    private static class Node {
        /** String stored in this Node. */
        private String s;
        /** Left child of this Node. */
        private Node left;
        /** Right child of this Node. */
        private Node right;

        /** Creates a Node containing SP. */
        Node(String sp) {
            s = sp;
        }
    }

    /** An iterator over BSTs. */
    private static class BSTIterator implements Iterator<String> {
        /** Stack of nodes to be delivered.  The values to be delivered
         *  are (a) the label of the top of the stack, then (b)
         *  the labels of the right child of the top of the stack inorder,
         *  then (c) the nodes in the rest of the stack (i.e., the result
         *  of recursively applying this rule to the result of popping
         *  the stack. */
        private Stack<Node> _toDo = new Stack<>();

        /** A new iterator over the labels in NODE. */
        BSTIterator(Node node) {
            addTree(node);
        }

        @Override
        public boolean hasNext() {
            return !_toDo.empty();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node node = _toDo.pop();
            addTree(node.right);
            return node.s;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /** Add the relevant subtrees of the tree rooted at NODE. */
        private void addTree(Node node) {
            while (node != null) {
                _toDo.push(node);
                node = node.left;
            }
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new BSTIterator(_root);
    }

    // FIXME: UNCOMMENT THE NEXT LINE FOR PART B
    // @Override
    public Iterator<String> iterator(String low, String high) {
        return null;  // FIXME: PART B (OPTIONAL)
    }


    /** Root node of the tree. */
    private Node _root;
}
