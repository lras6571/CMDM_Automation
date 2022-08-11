package com.sysco.qe.utils;

import org.junit.Test;
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

public class FolderKeyUtil {

    public static String retrieveKey() {
        String bucketName = "cmdm-outbound-incremental-data";
        S3Object object = null;

        SimpleDateFormat cstCdtFormat = new SimpleDateFormat("yyyy/M/dd/MM-dd-yyyy");
        cstCdtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateFormat = cstCdtFormat.format(new Date());

        int a = 333;
        for (int i = a; i > 326; i--) {
            LocalTime time = LocalTime.now();
            LocalTime newTime1 = time.minusMinutes(i);
            String latestTime1 = newTime1.toString().substring(0, 6);
            System.out.println(latestTime1);

            S3Client client = S3Client.builder().build();
            ListObjectsRequest request = ListObjectsRequest.builder().bucket(bucketName).prefix("billto/" + dateFormat + "-" + latestTime1).build();
            ListObjectsResponse response = client.listObjects(request);
            List<S3Object> objects = response.contents();
            ListIterator<S3Object> listIterator = objects.listIterator();

            while (listIterator.hasNext()) {
                object = listIterator.next();
                System.out.println(object.key());
            }
        }

        return object.key();
    }
}