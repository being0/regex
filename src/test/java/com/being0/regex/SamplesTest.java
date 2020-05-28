package com.being0.regex;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

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

}
