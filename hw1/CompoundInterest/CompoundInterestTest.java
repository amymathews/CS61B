import static org.junit.Assert.*;
import org.junit.Test;

public class CompoundInterestTest {

    @Test
    public void testNumYears() {
        /** Sample assert statement for comparing integers.*/

        assertEquals(3, CompoundInterest.numYears(2025));
        assertEquals(0, CompoundInterest.numYears(2022));
    }

    @Test
    public void testFutureValue() {
        // When working with decimals, we often want to specify a certain
        // range of "wiggle room", or tolerance. For example, if the answer
        // is 5.04, but anything between 5.02 and 5.06 would be okay too,
        // then we can do assertEquals(5.04, answer, .02).

        // The variable below can be used when you write your tests.
        double tolerance = 0.01;
        assertEquals(1050, CompoundInterest.futureValue(1000.00,5,2023), tolerance);
        assertEquals(1102.5, CompoundInterest.futureValue(1000.00,5,2024), tolerance);
        assertEquals(1157.625, CompoundInterest.futureValue(1000.00,5,2025), tolerance);


    }

    @Test
    public void testFutureValueReal() {

        double tolerance = 0.01;
        assertEquals(1029, CompoundInterest.futureValueReal(1000.00,5,2023, 2), tolerance);
        assertEquals(1058.841, CompoundInterest.futureValueReal(1000.00,5,2024,2), tolerance);

    }


    @Test
    public void testTotalSavings() {

        double tolerance = 0.01;
        assertEquals(16550, CompoundInterest.totalSavings(5000,2024,10),tolerance);
    }

    @Test
    public void testTotalSavingsReal() {

        double tolerance = 0.01;
        assertEquals(10390,CompoundInterest.totalSavingsReal(5000,2023,10,2),tolerance);
        assertEquals(16200.42,CompoundInterest.totalSavingsReal(5000,2024,10,2),tolerance);

    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(CompoundInterestTest.class));
    }
}
