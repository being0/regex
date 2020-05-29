package com.being0.regex;

import org.junit.Test;

import java.util.Random;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

public class SamplesTest {

    @Test
    public void testExtractExpParams() {
        // Prepare
        String exp = "hi ${firstname} ${lastname}!";
        Set<String> params = Samples.extractExpParams(exp);

        // Test
        assertThat(params, hasSize(2));
        assertThat(params, contains("firstname", "lastname"));
    }

    @Test
    public void testIsValidPassword_nullEmpty() {
        assertFalse(Samples.isValidPassword(null));
        assertFalse(Samples.isValidPassword(""));
    }

    @Test
    public void testIsValidPassword_size() {
        String passSize7 = "67I#z9p";
        assertEquals(7, passSize7.length());
        assertFalse(Samples.isValidPassword(passSize7));// Less than 8 is invalid
        assertTrue(Samples.isValidPassword(passSize7 + "1"));

        String passSize51 = "67I#z9p67I#z9p67I#z9p67I#z9p67I#z9p67I#z9p67I#z9p12";
        assertEquals(51, passSize51.length());
        assertFalse(Samples.isValidPassword(passSize51));// More than 50 is invalid
        assertEquals(50, passSize51.substring(0, 50).length());
        assertTrue(Samples.isValidPassword(passSize51.substring(0, 50)));
    }

    @Test
    public void testIsValidPassword_checkComplexity() {
        String wholeWordsPass = "i_am_invalid_pass";
        String wholeNonWordsPass = "@123456789$";

        assertFalse(Samples.isValidPassword(wholeWordsPass));
        // Adding a number make password valid
        assertTrue(Samples.isValidPassword(insertTokenAtRandomLocation(wholeWordsPass, "1")));
        // Adding a non-word make password valid
        assertTrue(Samples.isValidPassword(insertTokenAtRandomLocation(wholeWordsPass, "$")));

        assertFalse(Samples.isValidPassword(wholeNonWordsPass));
        assertTrue(Samples.isValidPassword(insertTokenAtRandomLocation(wholeNonWordsPass, "a")));
        assertTrue(Samples.isValidPassword(insertTokenAtRandomLocation(wholeNonWordsPass, "A")));
    }

    private static String insertTokenAtRandomLocation(String str, String token) {
        if (str == null) return token;

        int randomNum = new Random().nextInt(str.length() + 1);

        return str.substring(0, randomNum) + token + str.substring(randomNum);
    }

}
