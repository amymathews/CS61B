/**
 * TableFilter to filter for entries whose two columns match.
 *
 * @author Matthew Owen
 */
public class ColumnMatchFilter extends TableFilter {

    public ColumnMatchFilter(Table input, String colName1, String colName2) {
        super(input);
        _input = input;
        col1S = colName1;
        col2S = colName2;
        // FIXME: Add your code here.
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        /* REFERENCE! Whats the difference between == and equals()
         equals() method compares the two given strings based on the data / content of the string.
         If all the contents of both the strings are same then it returns true. If all characters
         are not matched then it returns false.

         If they are referring to the same object then return true, otherwise return false.
         We check for the memory location here.
        * */
        int col1 = _input.colNameToIndex(col1S);
        int col2 = _input.colNameToIndex(col2S);
        if(_next.getValue(col1).equals(_next.getValue(col2))){
            return true;
        }
        return false;
    }

    // FIXME: Add instance variables?
    Table _input;
    String col1S,col2S;
}
