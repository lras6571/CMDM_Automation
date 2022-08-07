package com.sysco.qe.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileReaderUtil {

    public static void readZip(final InputStream in, final String name) throws IOException {
        final ZipInputStream zin = new ZipInputStream(in);
        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null) {
            if (entry.getName().toLowerCase().endsWith(".zip")) {
                readZip(zin, name + "/" + entry.getName());
            } else {
                readFile(zin, entry.getName());
            }
        }
    }

    private static void readFile(final InputStream in, final String name) {
        String contents = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));
        System.out.println(String.format("Contents of %s: %s", name, contents));
    }
}
