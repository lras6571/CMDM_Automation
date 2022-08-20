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


    public static String readBillToNameFromFile(int accountName) {

        String columnBillToNameValue = fullValueLine[accountName];
        LoggerUtil.logINFO("Bill to Name in S3 Bucket is: " + columnBillToNameValue);
        return columnBillToNameValue.replace("\"", "");
    }


    public static String readBillToOpCoIDFromFile(int opco) {

        String columnSusCodeValueWithSpace= (fullValueLine[opco].split("Date"))[1];
        String columnSusCodeValue = columnSusCodeValueWithSpace.replaceAll("\\s+","");
        LoggerUtil.logINFO("Bill to OpCoID is in S3 Bucket is: " + fullValueLine[opco]);
        return columnSusCodeValue.replace("\"", "");
    }


    public static String readBillToStatusFromFile(int accountStatus) {

        String columnBillToAccountStatus = fullValueLine[accountStatus];
        LoggerUtil.logINFO("Bill To Account Status is in S3 Bucket is: " + fullValueLine[accountStatus]);
        return columnBillToAccountStatus.replace("\"", "");
    }


    public static String readBillToAddressFromFile(int billToAddressOne ) {

        String columnBillToAccountAddress = fullValueLine[billToAddressOne];
        LoggerUtil.logINFO("Bill To Account Address is in S3 Bucket is: " + fullValueLine[billToAddressOne]);
        return columnBillToAccountAddress.replace("\"", "");
    }


    public static String readBillToCityFromFile(int city) {

        String columnBillToAccountCity = fullValueLine[city];
        LoggerUtil.logINFO("Bill To Account City is in S3 Bucket is: " + fullValueLine[city]);
        return columnBillToAccountCity.replace("\"", "");
    }


    public static String readBillToStateFromFile(int state) {

        String columnBillToAccountState = fullValueLine[state];
        LoggerUtil.logINFO("Bill To Account State is in S3 Bucket is: " + fullValueLine[state]);
        return columnBillToAccountState.replace("\"", "");
    }


    public static String readBillToCountryFromFile(int countryCode) {

        String columnBillToAccountCountry = fullValueLine[countryCode];
        LoggerUtil.logINFO("Bill To Account Country is in S3 Bucket is: " + fullValueLine[countryCode]);
        return columnBillToAccountCountry.replace("\"", "");
    }


    public static String readBillToPostalCodeFromFile(int postalCode) {

        String columnBillToAccountPostalCode = fullValueLine[postalCode];
        LoggerUtil.logINFO("Bill To Account Postal Code is in S3 Bucket is: " + fullValueLine[postalCode]);
        return columnBillToAccountPostalCode.replace("\"", "");
    }


    public static String readBillToTelephoneNumberFromFile(int telephone) {

        String columnBillToAccountTelephoneNumber = fullValueLine[28];
        LoggerUtil.logINFO("Bill To Account Telephone Number is in S3 Bucket is: " + fullValueLine[telephone]);
        return columnBillToAccountTelephoneNumber.replace("\"", "");
    }


    public static String readBillToSourceSystemFromFile(int sourceSystem) {

        String columnBillToAccountSourceSystem = fullValueLine[sourceSystem].trim();
        LoggerUtil.logINFO("Bill To Account Source System is in S3 Bucket is: " + fullValueLine[sourceSystem]);
        return columnBillToAccountSourceSystem.replace("\"", "");
    }


    public static String readBillToAccountNumberFromFile(int strAccountNumber) {

        String columnBillToAccountNumber = fullValueLine[strAccountNumber];
        LoggerUtil.logINFO("Bill To Account Number is in S3 Bucket is: " + fullValueLine[strAccountNumber]);
        return columnBillToAccountNumber.replace("\"", "");
    }


    public static String readShipToAccountNameFromFile(int strAccountName) {

        String columnBillToNameValue = fullValueLine[strAccountName];
        LoggerUtil.logINFO("Ship To Account Name in S3 Bucket is: " + columnBillToNameValue);
        return columnBillToNameValue.replace("\"", "");
    }


    public static String readShipToAccountStatusFromFile(int strStatus) {

        String columnBillToAccountStatus = fullValueLine[strStatus];
        LoggerUtil.logINFO("Ship To Account Status is in S3 Bucket is: " + fullValueLine[strStatus]);
        return columnBillToAccountStatus.replace("\"", "");
    }


    public static String readShipToAddressFromFile(int shipAddressOne) {

        String columnBillToAccountAddress = fullValueLine[shipAddressOne];
        LoggerUtil.logINFO("Ship To Account Address is in S3 Bucket is: " + fullValueLine[shipAddressOne]);
        return columnBillToAccountAddress.replace("\"", "");
    }


    public static String readShipToCityFromFile(int city) {

        String columnBillToAccountCity = fullValueLine[city];
        LoggerUtil.logINFO("Ship To Account City is in S3 Bucket is: " + fullValueLine[city]);
        return columnBillToAccountCity.replace("\"", "");
    }


    public static String readShipToStateFromFile(int state) {

        String columnBillToAccountState = fullValueLine[state];
        LoggerUtil.logINFO("Ship To Account State is in S3 Bucket is: " + fullValueLine[state]);
        return columnBillToAccountState.replace("\"", "");
    }


    public static String readShipToTelephoneNumberFromFile(int telephone) {

        String columnBillToAccountTelephoneNumber = fullValueLine[telephone];
        LoggerUtil.logINFO("Ship To Account Telephone Number is in S3 Bucket is: " + fullValueLine[telephone]);
        return columnBillToAccountTelephoneNumber.replace("\"", "");
    }


    public static String readShipToPostalCodeFromFile(int postalCode) {

        String columnBillToAccountPostalCode = fullValueLine[postalCode];
        LoggerUtil.logINFO("Ship To Account Postal Code is in S3 Bucket is: " + fullValueLine[postalCode]);
        return columnBillToAccountPostalCode.replace("\"", "");
    }


    public static String readShipToCountryFromFile(int countryCode) {

        String columnBillToAccountCountry = fullValueLine[countryCode];
        LoggerUtil.logINFO("Ship To Account Country is in S3 Bucket is: " + fullValueLine[countryCode]);
        return columnBillToAccountCountry.replace("\"", "");
    }


    public static String readShipToOpCoIDFromFile(int opco) {

        String columnSusCodeValueWithSpace= (fullValueLine[opco].split("Ship-To"))[1];
        String columnSusCodeValue = columnSusCodeValueWithSpace.replaceAll("\\s+","");
        LoggerUtil.logINFO("Ship To OpCoID is in S3 Bucket is: " + fullValueLine[opco]);
        return columnSusCodeValue.replace("\"", "");
    }


    public static String readShipToSourceSystemFromFile(int sourceSystem) {

        String columnBillToAccountSourceSystem = fullValueLine[sourceSystem].trim();
        LoggerUtil.logINFO("Ship To Account Source System is in S3 Bucket is: " + fullValueLine[sourceSystem]);
        return columnBillToAccountSourceSystem.replace("\"", "");
    }


    public static String readShipToStoreNumberFromFile(int storeNumber) {

        String columnBillToAccountSourceSystem = fullValueLine[storeNumber].trim();
        LoggerUtil.logINFO("Ship To Account Store Number is in S3 Bucket is: " + fullValueLine[storeNumber]);
        return columnBillToAccountSourceSystem.replace("\"", "");
    }


    public static String readShipToAccountNumberFromFile(int shipToAccountNumber) {

        String columnBillToAccountSourceSystem = fullValueLine[shipToAccountNumber].trim();
        LoggerUtil.logINFO("Ship To Account Number is in S3 Bucket is: " + fullValueLine[shipToAccountNumber]);
        return columnBillToAccountSourceSystem.replace("\"", "");
    }


    public static String readShipToVendorNumberFromFile(int shipToVendorNumber) {

        String columnBillToAccountSourceSystem = fullValueLine[shipToVendorNumber].trim();
        LoggerUtil.logINFO("Ship To Vendor Number is in S3 Bucket is: " + fullValueLine[shipToVendorNumber]);
        return columnBillToAccountSourceSystem.replace("\"", "");
    }


    public static String readBillToVendorNumberFromFile(int shipToVendorNumber) {

        String columnBillToAccountSourceSystem = fullValueLine[shipToVendorNumber].trim();
        LoggerUtil.logINFO("Ship To Vendor Number is in S3 Bucket is: " + fullValueLine[shipToVendorNumber]);
        return columnBillToAccountSourceSystem.replace("\"", "");
    }
}
