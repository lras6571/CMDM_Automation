package com.sysco.qe.utils;

import com.syscolab.qe.core.common.LoggerUtil;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.TimeZone;

import static com.sysco.qe.common.APIConstants.BUCKET_NAME;
import static java.lang.Thread.sleep;

public class FolderKeyUtil {

    private FolderKeyUtil() {
    }

    public static String retrieveKey(String type) throws InterruptedException {
        sleep(180000);
        String bucketName = BUCKET_NAME;
        S3Object object = null;

        SimpleDateFormat cstCdtFormat = new SimpleDateFormat("yyyy/M/dd/MM-dd-yyyy");
        cstCdtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateFormat = cstCdtFormat.format(new Date());

        int a = 333;
        for (int i = a; i > 326; i--) {
            LocalTime time = LocalTime.now();
            LocalTime newTime = time.minusMinutes(i);
            String latestTime = newTime.toString().substring(0, 6);

            S3Client client = S3Client.builder().build();
            ListObjectsRequest request = ListObjectsRequest.builder().bucket(bucketName).prefix(type + "/" + dateFormat + "-" + latestTime).build();
            LoggerUtil.logINFO(type + "/" + dateFormat + "-" + latestTime);
            ListObjectsResponse response = client.listObjects(request);
            List<S3Object> objects = response.contents();
            ListIterator<S3Object> listIterator = objects.listIterator();

            while (listIterator.hasNext()) {
                object = listIterator.next();
                LoggerUtil.logINFO(object.key());
            }
        }

        return object.key();
    }
}