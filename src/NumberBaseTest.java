import org.junit.Assert;
import org.junit.Test;

/**
 * Example unit tests for the NumberBase.conversions method
 *
 * @author Zachary Kurmas
 * @modifier Jared Moore
 */
// Created  8/26/13 at 11:48 AM
// (C) Zachary Kurmas 2013
// Modified 07/26/2016 by Jared Moore

public class NumberBaseTest {

    @Test
    public void decimal_to_binary() throws Throwable {
        Assert.assertEquals("0", NumberBase.convert("0", 10, 2));
        Assert.assertEquals("1", NumberBase.convert("1", 10, 2));
        Assert.assertEquals("1010", NumberBase.convert("10", 10, 2));
        Assert.assertEquals("1000011110001", NumberBase.convert("4337", 10, 2));
    }

    @Test
    public void binary_to_decimal() throws Throwable {
        Assert.assertEquals("0", NumberBase.convert("0", 2, 10));
        Assert.assertEquals("1", NumberBase.convert("1", 2, 10));
        Assert.assertEquals("2", NumberBase.convert("10", 2, 10));
        Assert.assertEquals("3", NumberBase.convert("11", 2, 10));
        Assert.assertEquals("10", NumberBase.convert("1010", 2, 10));
        Assert.assertEquals("4337", NumberBase.convert("1000011110001", 2, 10));
    }

    @Test
    public void decimal_to_hex() throws Throwable {
        Assert.assertEquals("0", NumberBase.convert("0", 10, 16));
        Assert.assertEquals("1", NumberBase.convert("1", 10, 16));
        Assert.assertEquals("a", NumberBase.convert("10", 10, 16));
        Assert.assertEquals("64", NumberBase.convert("100", 10, 16));
        Assert.assertEquals("dead", NumberBase.convert("57005", 10, 16));
    }

    // Remember:  When looking for an exception, you can do only *one* test
    // per method.
    @Test(expected = IllegalArgumentException.class)
    public void input_is_valid1() {
        NumberBase.convert("14d", 10, 2);
    }


    // Write other tests for different base pairs and edge cases!
}