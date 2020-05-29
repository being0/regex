package com.being0.regex;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Samples {
    /**
     * anything between ( and ) will be grouped, The regex catch anything starting by ${ and ending by }
     * and catch the inside parameter as a group
     */
    private static final Pattern EXP_EXTRACTOR_PATTERN = Pattern.compile("\\$\\{([^}]+)}"); // Extract expression like ${myexp}

    /**
     * Using positive look ahead technic to check password validity.
     * The password should be between 8~50 characters including nonwords or number characters and at least one word character.
     * Note: ^.* means start of string followed by zero or more of any character (except line break)
     */
    private static final Pattern PASSWORD_CHECk_PATTERN = Pattern.compile("(?=^.{8,50}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Za-z]).*$");

    /*
     * Extract all parameters stating by ${ and ending by } from expIncluded string
     *
     * @param expIncluded exp string like for example "hi ${firstname} ${lastname}!"
     * @return set of extracted parameters, in the above example firstname, lastname
     */
    public static Set<String> extractExpParams(String expIncluded) {
        HashSet<String> params = new HashSet<>();
        if (expIncluded == null) return null;

        Matcher matcher = EXP_EXTRACTOR_PATTERN.matcher(expIncluded);

        while (matcher.find()) params.add(matcher.group(1));

        return params;
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) return false;

        return PASSWORD_CHECk_PATTERN.matcher(password).find();
    }

}
