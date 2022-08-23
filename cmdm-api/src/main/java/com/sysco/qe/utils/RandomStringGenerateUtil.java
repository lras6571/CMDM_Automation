package com.sysco.qe.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Locale;

public class RandomStringGenerateUtil {

    private RandomStringGenerateUtil() {
    }

    //Generate a random 3 character value.
    public static String randomStringGenerator() {

        return RandomStringUtils.randomAlphanumeric(3).toUpperCase(Locale.ROOT);
    }
}
