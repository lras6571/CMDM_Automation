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

    private ZipFileReaderUtil() {
    }

    public static String readZip(final InputStream in, final String name) throws IOException {
        final ZipInputStream zin = new ZipInputStream(in);
        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null) {
            if (entry.getName().toLowerCase().endsWith(".zip")) {
                readZip(zin, name + "/" + entry.getName());
            } else {
                readFile(zin, entry.getName());
            }
        }
        return columnValue.replaceAll("^\"|\"$", "");
    }

    private static void readFile(final InputStream in, final String name) throws IOException {
        String contents = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));
        LoggerUtil.logINFO(String.format("Contents of %s: %s", name, contents));
        String[] fullValueLine = contents.split("\\|");
        columnValue = fullValueLine[20];
        LoggerUtil.logINFO("Bill to Name in S3 Bucket is: " + fullValueLine[20]);
    }
}
