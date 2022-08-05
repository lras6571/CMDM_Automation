package com.sysco.qe.utils;

import com.syscolab.qe.core.common.LoggerUtil;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileOperationUtil {

    public static String INPUT_ZIP_FILE = "C:\\Sysco\\Projects\\Automation\\cmdm-automation\\test.zip";
    public static String OUTPUT_FOLDER = "C:\\Sysco\\Projects\\Automation\\cmdm-automation";

    public static void copyInputStream(InputStream in, OutputStream out)
            throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    public static void unZipIt() {

        try {

            ZipFile zipFile = new ZipFile(INPUT_ZIP_FILE);

            Enumeration zipEntries = zipFile.entries();

            File OUTFILEFOLD = new File(OUTPUT_FOLDER);
            if (!OUTFILEFOLD.exists()) {
                OUTFILEFOLD.mkdir();
            }
            String OUTDIR = OUTPUT_FOLDER + File.separator;
            while (zipEntries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) zipEntries.nextElement();

                if (zipEntry.isDirectory()) {
                    LoggerUtil.logINFO("Extracting directory: " + OUTDIR + zipEntry.getName());

                    new File(OUTDIR + zipEntry.getName()).mkdir();
                    continue;
                }

                LoggerUtil.logINFO("Extracting file: " + OUTDIR + zipEntry.getName());

                copyInputStream(zipFile.getInputStream(zipEntry), new BufferedOutputStream(new FileOutputStream(OUTDIR + zipEntry.getName().replace(":", ""))));
            }

            zipFile.close();

        } catch (IOException ioe) {
            LoggerUtil.logERROR("Unhandled exception:", ioe);
            ioe.printStackTrace();
            return;
        }
    }

}
