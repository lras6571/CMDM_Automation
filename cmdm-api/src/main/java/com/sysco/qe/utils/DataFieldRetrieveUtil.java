package com.sysco.qe.utils;

import com.opencsv.CSVReader;

import java.io.FileReader;

import static com.sysco.qe.common.APIConstants.INPUT_BILL_TO_DATA_CSV_FILE2;

public class DataFieldRetrieveUtil {

    private DataFieldRetrieveUtil() {
    }

    // Main driver method
    public static String retrieveData(int columnNumber) {
        CSVReader reader = null;
        String[] strAr1 = new String[0];
        try {
//            reader = new CSVReader(new FileReader("C:\\Users\\Dell\\Downloads\\FF_BILL_TO_clean_293_TEMP.csv"));
            reader = new CSVReader(new FileReader(INPUT_BILL_TO_DATA_CSV_FILE2));
            String[] nextLine;
            //read one line at a time
            while ((nextLine = reader.readNext()) != null) {
                for (String token : nextLine) {
                    System.out.println("Readings from the S3 Bucket is: "+token);
                    strAr1 = token.split("\\|");
                    System.out.println("Bill to Name is: " + strAr1[columnNumber]);
                }
                System.out.print("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strAr1[columnNumber];
    }
}