/**
 * TableFilter to filter for containing substrings.
 *
 * @author Matthew Owen
 */
public class SubstringFilter extends TableFilter {

    public SubstringFilter(Table input, String colName, String subStr) {
        super(input);
        // FIXME: Add your code here.
        _input = input;
        colS = colName;
        substrS = subStr;
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        int col= _input.colNameToIndex(colS);
        if(_next.getValue(col).contains(substrS)){
            return true;
        }
        return false;
    }

    // FIXME: Add instance variables?
    Table _input;
    String colS,substrS;
}
