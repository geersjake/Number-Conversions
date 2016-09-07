import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/*****************************************************************
 Test for numberBase class

 @author Jake Geers
 @version 9/7/2016
 *****************************************************************/

public class NumberBaseTest {

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
     Tests that exceptions are thrown when input is non-alphanumeric
     @throws IllegalArgumentException
     *****************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testAlphaNum () throws Exception {
        NumberBase.convert("$%#", 2, 3);
    }

    /*****************************************************************
     Tests that exceptions are thrown when input is uppercase
     @throws IllegalArgumentException
     *****************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testUpperCase () throws Exception {
        NumberBase.convert("A5", 16, 3);
    }

    /*****************************************************************
     Tests that exceptions are thrown when input contains spaces
     @throws IllegalArgumentException
     *****************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testSpaces () throws Exception {
        NumberBase.convert("1 4", 5, 3);
    }

    /*****************************************************************
     Tests that exceptions are thrown when input is null
     @throws IllegalArgumentException
     *****************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testNull () throws Exception {
        NumberBase.convert(null, 2, 3);
    }

    /*****************************************************************
     Tests that exceptions are thrown when input string is empty
     @throws IllegalArgumentException
     *****************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testEmpty () throws Exception {
        NumberBase.convert("", 2, 3);
    }

    /*****************************************************************
     Tests that exceptions are thrown when input is incompatible with
     the give base i.e a base ten number cannot have letters
     @throws IllegalArgumentException
     *****************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testIncompatible () throws Exception {
        NumberBase.convert("4c", 2, 3);
    }

    /*****************************************************************
     Tests that exceptions are thrown when input is incompatible with
     the give base i.e a base ten number cannot have letters
     @throws IllegalArgumentException
     *****************************************************************/
    @Test (expected = IllegalArgumentException.class)
    public void testIncompatible2 () throws Exception {
        NumberBase.convert("24z", 16, 2);
    }
}