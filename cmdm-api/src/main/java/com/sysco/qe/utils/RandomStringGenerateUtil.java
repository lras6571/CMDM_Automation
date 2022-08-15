package com.sysco.qe.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Locale;

public class RandomStringGenerateUtil {

    private RandomStringGenerateUtil(){
    }

    public static String randomStringGenerator() {

        return RandomStringUtils.randomAlphanumeric(3).toUpperCase(Locale.ROOT);
    }
}
