/** P2Pattern class
 *  @author Josh Hug & Vivant Sakore
 */

public class P2Pattern {
    /* Pattern to match a valid date of the form MM/DD/YYYY. Eg: 9/22/2019 */
    public static String P1 = "([0]?[1-9]|1[0-2])/([0]?[1-9]|[12][0-9]|3[01])/[1-9]\\d\\d\\d"; //FIXME: Add your regex here

    /** Pattern to match 61b notation for literal IntLists. */
    public static String P2 = "\\([0-9]+(\\, +[0-9]+)+\\)"; //FIXME: Add your regex here

    /* Pattern to match a valid domain name. Eg: www.support.facebook-login.com */
    public static String P3 = ""; //FIXME: Add your regex here

    /* Pattern to match a valid java variable name. Eg: _child13$ */
    public static String P4 = "[[a-z][A-Z]$_][[a-z][A-Z][0-9]$_]*"; //FIXME: Add your regex here

    /* Pattern to match a valid IPv4 address. Eg: 127.0.0.1 */
    public static String P5 = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$"; //FIXME: Add your regex here

}
