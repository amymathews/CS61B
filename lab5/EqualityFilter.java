/**
 * TableFilter to filter for entries equal to a given string.
 *
 * @author Matthew Owen
 */
public class EqualityFilter extends TableFilter {

    public EqualityFilter(Table input, String colName, String match) {
        super(input);
        // FIXME: Add your code here.
        _input = input;
        colS = colName;
        matchS = match;

    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        int col = _input.colNameToIndex(colS);
        if(_next.getValue(col).equals(matchS)){
            return true;
        }
        return false;
    }

    // FIXME: Add instance variables?

    Table _input;
    String colS,matchS;
}
