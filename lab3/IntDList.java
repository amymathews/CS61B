/**
 * Scheme-like pairs that can be used to form a list of integers.
 *
 * @author P. N. Hilfinger; updated by Linda Deng (1/26/2022)
 */
public class IntDList {

    /**
     * First and last nodes of list.
     */
    protected DNode _front, _back;

    /**
     * An empty list.
     */
    public IntDList() {
        _front = _back = null;
    }

    /**
     * @param values the ints to be placed in the IntDList.
     */
    public IntDList(Integer... values) {
        _front = _back = null;
        for (int val : values) {
            insertBack(val);
        }
    }

    /**
     * @return The first value in this list.
     * Throws a NullPointerException if the list is empty.
     */
    public int getFront() {
        return _front._val;
    }

    /**
     * @return The last value in this list.
     * Throws a NullPointerException if the list is empty.
     */
    public int getBack() {
        return _back._val;
    }

    /**
     * @return The number of elements in this list.
     */
    public int size() {
        // TODO: Implement this method and return correct value
        //check if list contains no values
        if(_front == null)
            return 0;
        //check if next value of list is null -> contains one value
        else if(_front._next == null)
            return 1;
        else {
            DNode holder = _front;
            int flag = 0;
            while (holder != null) {
                holder = holder._next;
                flag = flag + 1;
            }
            return flag;
        }
    }

    /**
     * @param index index of node to return,
     *          where index = 0 returns the first node,
     *          index = 1 returns the second node, and so on.
     *          You can assume index will always be a valid index,
     *              i.e 0 <= index < size.
     * @return The node at index index
     */
    private DNode getNode(int index) {
        // TODO: Implement this method and return correct node
        DNode here = _front;
        int counter = 0;
        while(here != null){
            if(counter == index){
                return here;
            }
            counter+=1;
            here = here._next;
        }
        return null;
    }

    /**
     * @param index index of element to return,
     *          where index = 0 returns the first element,
     *          index = 1 returns the second element,and so on.
     *          You can assume index will always be a valid index,
     *              i.e 0 <= index < size.
     * @return The integer value at index index
     */
    public int get(int index) {
        // TODO: Implement this method (Hint: use `getNode`)
        DNode answer = getNode(index);
        return answer._val;
    }

    /**
     * @param d value to be inserted in the front
     */
    public void insertFront(int d) {
        // TODO: Implement this method
        /*if this the first element we are adding to the list,
         the front and the back points to that element*/
        if(_front == null){
            DNode element = new DNode(d);
            _front = element;
            _back = element;
        }
        else{
            DNode element = new DNode(d); // create a new node
            element._next = _front; //pt new_front's next to old _front
            _front._prev = element; // pt old_front's prev to new _front
            _front = element; // update new front ptr
        }
    }

    /**
     * @param d value to be inserted in the back
     */
    public void insertBack(int d) {
        // TODO: Implement this method
        if(_front == null){
            DNode element = new DNode(d);
            _front = element;
            _back = _front;
        } else{
            DNode element = new DNode(d); // create a new node
            element._prev = _back;
            _back._next = element;
            _back = element;

        }
    }

    /**
     * @param d     value to be inserted
     * @param index index at which the value should be inserted
     *              where index = 0 inserts at the front,
     *              index = 1 inserts at the second position, and so onh.
     *              You can assume index will always be a valid index,
     *              i.e 0 <= index <= size.
     */
    public void insertAtIndex(int d, int index) {
        // TODO: Implement this method
        DNode element = new DNode(d);
        DNode lst = _front;
//        int flag = 0;
//        //empty list
//        if(lst == null || index == 0){
//
//            insertFront(d);
//        }
//        //only one element
//        else if(lst._next == null){
//            //checks if we should use insertFront/insertBack depending on index location.
//            if(index == 0){
//                insertFront(d);
//            }
//            else {
//                insertBack(d);
//            }
//        }
                if(index == 0 || lst == null){
                    insertFront(d);
                }
                else if(index == size()){
                    insertBack(d);
                }
                else{
                    DNode curr = getNode(index);
//                    curr._next = element;
//                    element._prev = curr._prev;
//                    curr._prev._next= element;
//                    curr._prev = element;

                    curr._prev._next = element;
                    element._next = curr;
                    element._prev = curr._prev;
                    curr._prev = element;

                }
    }



    /**
     * Removes the first item in the IntDList and returns it.
     * Assume `deleteFront` is never called on an empty IntDList.
     *
     * @return the item that was deleted
     */
    public int deleteFront() {
        // TODO: Implement this method and return correct value
        int deleted = _front._val;
        if(_front._next == null){
            DNode empty = _front;
            _front = null;
            return empty._val;
        }
        _front = _front._next;
        _front._prev = null;
        return deleted;

    }

    /**
     * Removes the last item in the IntDList and returns it.
     * Assume `deleteBack` is never called on an empty IntDList.
     *
     * @return the item that was deleted
     */
    public int deleteBack() {
        // TODO: Implement this method and return correct value
        if(_front._next == null){
            DNode old_front = _front;
            _front = null;
            return old_front._val;
        }else{
            DNode old_back = _back;
            _back = _back._prev;
            _back._next = null;
            return old_back._val;
        }
    }

    /**
     * @param index index of element to be deleted,
     *          where index = 0 returns the first element,
     *          index = 1 will delete the second element, and so on.
     *          You can assume index will always be a valid index,
     *              i.e 0 <= index < size.
     * @return the item that was deleted
     */
    public int deleteAtIndex(int index) {
        // TODO: Implement this method and return correct value
        return 0;
    }

    /**
     * @return a string representation of the IntDList in the form
     * [] (empty list) or [1, 2], etc.
     * Hint:
     * String a = "a";
     * a += "b";
     * System.out.println(a); //prints ab
     */
    public String toString() {
        // TODO: Implement this method to return correct value
        if (size() == 0) {
            return "[]";
        }
        String str = "[";
        DNode curr = _front;
        for (; curr._next != null; curr = curr._next) {
            str += curr._val + ", ";
        }
        str += curr._val +"]";
        return str;
    }

    /**
     * DNode is a "static nested class", because we're only using it inside
     * IntDList, so there's no need to put it outside (and "pollute the
     * namespace" with it. This is also referred to as encapsulation.
     * Look it up for more information!
     */
    static class DNode {
        /** Previous DNode. */
        protected DNode _prev;
        /** Next DNode. */
        protected DNode _next;
        /** Value contained in DNode. */
        protected int _val;

        /**
         * @param val the int to be placed in DNode.
         */
        protected DNode(int val) {
            this(null, val, null);
        }

        /**
         * @param prev previous DNode.
         * @param val  value to be stored in DNode.
         * @param next next DNode.
         */
        protected DNode(DNode prev, int val, DNode next) {
            _prev = prev;
            _val = val;
            _next = next;
        }
    }

}
