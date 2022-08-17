package com.sysco.qe.utils;

import com.opencsv.CSVReader;
import com.syscolab.qe.core.common.LoggerUtil;

import java.io.FileReader;

import static com.sysco.qe.common.APIConstants.INPUT_BILL_TO_DATA_CSV_FILE2;

public class DataFieldRetrieveUtil {

    private DataFieldRetrieveUtil() {
    }

    // Main driver method
    public static String retrieveData(int columnNumber, String filePath) {
        CSVReader reader = null;
        String[] strAr1 = new String[0];
        try {
            reader = new CSVReader(new FileReader(filePath));
            String[] nextLine;
            //read one line at a time
            while ((nextLine = reader.readNext()) != null) {
                for (String token : nextLine) {
                    LoggerUtil.logINFO("Readings from the Upload File is: "+token);
                    strAr1 = token.split("\\|");
                    LoggerUtil.logINFO("CSV File Value is: " + strAr1[columnNumber]);
                }
                System.out.print("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strAr1[columnNumber];
    }
}