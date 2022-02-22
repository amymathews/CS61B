/**
 * TableFilter to filter for entries greater than a given string.
 *
 * @author Matthew Owen
 */
public class GreaterThanFilter extends TableFilter {

    public GreaterThanFilter(Table input, String colName, String ref) {
        super(input);
        // FIXME: Add your code here.
        _input = input;
        colS = colName;
        refS = ref;
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        int col = _input.colNameToIndex(colS);
        if(_next.getValue(col).length() > refS.length()){
            return true;
        }
        return false;
    }

    // FIXME: Add instance variables?
    Table _input;
    String colS, refS;
}
