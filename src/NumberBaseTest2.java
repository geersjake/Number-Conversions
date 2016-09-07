import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/*****************************************************************
 Test for numberBase class

 @author Jake Geers
 @version 9/7/2016
 *****************************************************************/

public class NumberBaseTest2 {

    /** Interface rule for exceptions */
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /** Globally used general exception */
    Exception ex;

    /** Random number generator */
    Random rand;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    /*****************************************************************
     Test for converting binary into base 10
     @throws IllegalArgumentException
     *****************************************************************/
    @Test
    public void binaryToBaseTen() throws Exception {
        for (int i = 0; i < 100; i++) {
            rand = new Random();
            int n = rand.nextInt(100000);
            String num_in_binary = Integer.toBinaryString(n);
            String num_in_decimal = Integer.toString(n);
            assertEquals(num_in_decimal, NumberBase.convert(num_in_binary, 2, 10));
        }
    }

    /*****************************************************************
     Converts 1000 random inputs in random bases to random bases
     @throws IllegalArgumentException
     *****************************************************************/
    @Test
    public void testAll() throws Exception {
        for (int i = 0; i < 1000; i++) {
            rand = new Random();
            int input = rand.nextInt(2147483647); //max input num
            int baseIn = rand.nextInt(35)+2; //rand num from 2-36
            int baseOut = rand.nextInt(35)+2; //rand num from 2-36
            String input_as_str = Integer.toString(input);
            String input_as_rnd_base = Integer.toString(input, baseIn); //converting input to baseIn base
            try {
                assertEquals(input_as_str, NumberBase.convert(input_as_rnd_base, baseIn, baseOut));
            } catch (AssertionError ex ){
                System.out.println("Input: " + input);
                System.out.println("baseIn: " + baseIn);
                System.out.println("baseOut: " + baseOut);
            }
        }
    }

    /*****************************************************************
     Tests that exceptions are thrown when input is invalid
     @throws IllegalArgumentException
     *****************************************************************/
    @Test
    public void testInput () throws Exception {

    }

}