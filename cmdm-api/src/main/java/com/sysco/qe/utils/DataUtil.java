package com.sysco.qe.utils;

import com.sysco.qeutils.utils.ResponseUtils;
import com.syscolab.qe.core.common.LoggerUtil;
import lombok.SneakyThrows;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataUtil {

    DataUtil() {
    }

    public static Object loadTestData(String pathToFileFromResources, Class<?> c) {
        return (ResponseUtils.getObject(readJson(pathToFileFromResources), c));
    }

    /**
     * This method is used read the json file
     *
     * @param pathToFileFromResources - Path to the json file from resources directory
     * @return
     */
    private static String readJson(String pathToFileFromResources) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(pathToFileFromResources)));
        } catch (IOException e) {
            LoggerUtil.logERROR("Exception Occurred During read the Json", e);
        }
        return content;
    }

    /**
     * This method is used read the csv file
     *
     * @param pathToFileFromResources - Path to the csv file from resources directory
     * @param lineNumber - Row number of csv file
     * @return Account Name
     */
    public static String getAccountNameFromCsv(String pathToFileFromResources, int lineNumber) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(pathToFileFromResources));
        List<String> lines = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        String fullLine = lines.get(lineNumber);
        String[] entityName = fullLine.split("\\|");

        return entityName[2];

    }

    /**
     * This method is used read the csv file
     *
     * @param pathToFileFromResources - Path to the csv file from resources directory
     * @return csv data as a List
     */
    @SneakyThrows
    public static List<Map<String, String>> readCSVAsListOfMaps(String pathToFileFromResources) {

        List<String> rowAsTokens;
        Map<String, String> row;
        List<Map<String, String>> arrayList = new ArrayList<>();

        try (CsvListReader csvReader = new CsvListReader(new FileReader(pathToFileFromResources), CsvPreference.STANDARD_PREFERENCE)) {
            String csvHeaderLine = csvReader.read().get(0);
            List<String> csvHeader = Arrays.asList(csvHeaderLine.split("\\|"));
            while ((rowAsTokens = csvReader.read()) != null) {
                row = new HashMap<>();
                for (int i = 0; i < csvHeader.size(); i++) {
                    row.put(csvHeader.get(i), rowAsTokens.get(0).split("\\|")[i]);
                }
                arrayList.add(row);
            }
            return arrayList;
        }
    }

}
