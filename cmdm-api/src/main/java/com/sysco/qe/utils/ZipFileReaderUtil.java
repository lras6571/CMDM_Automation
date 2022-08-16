package com.sysco.qe.utils;

import com.syscolab.qe.core.common.LoggerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileReaderUtil {

    private static String columnValue;
    private static String[] fullValueLine;

    private ZipFileReaderUtil() {
    }

    public static String[] readZip(final InputStream in, final String name) throws IOException {
        final ZipInputStream zin = new ZipInputStream(in);
        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null) {
            if (entry.getName().toLowerCase().endsWith(".zip")) {
                readZip(zin, name + "/" + entry.getName());
            } else {
                readFile(zin, entry.getName());
            }
        }
        return fullValueLine;
    }


    public static void readFile(final InputStream in, final String name){

        String contents = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));
        LoggerUtil.logINFO(String.format("Contents of %s: %s", name, contents));
        fullValueLine = contents.split("\\|");
    }


    public static String readBillToNameFromFile() {

        String columnBillToNameValue = fullValueLine[20];
        LoggerUtil.logINFO("Bill to Name in S3 Bucket is: " + columnBillToNameValue);
        return columnBillToNameValue.replace("\"", "");
    }


    public static String readOpCoIDFromFile() {

        String columnSusCodeValueWithSpace= (fullValueLine[16].split("Date"))[1];
        String columnSusCodeValue = columnSusCodeValueWithSpace.replaceAll("\\s+","");
        LoggerUtil.logINFO("OpCoID is in S3 Bucket is: " + fullValueLine[16]);
        return columnSusCodeValue.replace("\"", "");
    }


    public static String readBillToAccountNumberFromFile() {

        String columnBillToAccountNumber = fullValueLine[18];
        LoggerUtil.logINFO("Bill To Account Number is in S3 Bucket is: " + fullValueLine[18]);
        return columnBillToAccountNumber.replace("\"", "");
    }


    public static String readBillToStatusFromFile() {

        String columnBillToAccountStatus = fullValueLine[21];
        LoggerUtil.logINFO("Bill To Account Status is in S3 Bucket is: " + fullValueLine[21]);
        return columnBillToAccountStatus.replace("\"", "");
    }


    public static String readBillToAddressFromFile() {

        String columnBillToAccountAddress = fullValueLine[22];
        LoggerUtil.logINFO("Bill To Account Address is in S3 Bucket is: " + fullValueLine[22]);
        return columnBillToAccountAddress.replace("\"", "");
    }


    public static String readBillToCityFromFile() {

        String columnBillToAccountCity = fullValueLine[24];
        LoggerUtil.logINFO("Bill To Account City is in S3 Bucket is: " + fullValueLine[24]);
        return columnBillToAccountCity.replace("\"", "");
    }


    public static String readBillToStateFromFile() {

        String columnBillToAccountState = fullValueLine[25];
        LoggerUtil.logINFO("Bill To Account State is in S3 Bucket is: " + fullValueLine[25]);
        return columnBillToAccountState.replace("\"", "");
    }


    public static String readBillToCountryFromFile() {

        String columnBillToAccountCountry = fullValueLine[26];
        LoggerUtil.logINFO("Bill To Account Country is in S3 Bucket is: " + fullValueLine[26]);
        return columnBillToAccountCountry.replace("\"", "");
    }


    public static String readBillToPostalCodeFromFile() {

        String columnBillToAccountPostalCode = fullValueLine[27];
        LoggerUtil.logINFO("Bill To Account Postal Code is in S3 Bucket is: " + fullValueLine[27]);
        return columnBillToAccountPostalCode.replace("\"", "");
    }


    public static String readBillToTelephoneNumberFromFile() {

        String columnBillToAccountTelephoneNumber = fullValueLine[28];
        LoggerUtil.logINFO("Bill To Account Telephone Number is in S3 Bucket is: " + fullValueLine[28]);
        return columnBillToAccountTelephoneNumber.replace("\"", "");
    }


    public static String readBillToSourceSystemFromFile() {

        String columnBillToAccountSourceSystem = fullValueLine[31].trim();
        LoggerUtil.logINFO("Bill To Account Source System is in S3 Bucket is: " + fullValueLine[31]);
        return columnBillToAccountSourceSystem.replace("\"", "");
    }


}
