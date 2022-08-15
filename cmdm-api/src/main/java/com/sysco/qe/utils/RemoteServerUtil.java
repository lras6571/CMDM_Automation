package com.sysco.qe.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.syscolab.qe.core.common.LoggerUtil;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;

import static com.sysco.qe.common.APIConstants.STIBO_IMPORT_WAIT;

public class RemoteServerUtil {

    public static void uploadFileToRemoteServer (String host, String user, String password, String remoteDirectory, String localFile) {

        String SFTPHOST = host;
        int SFTPPORT = 22;
        String SFTPUSER = user;
        String SFTPPASS = password;
        String SFTPWORKINGDIR = remoteDirectory;

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;

        System.out.println("Preparing the Host Information for sftp.");

        try {

            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            System.out.println("Host Connected.");

            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("sftp channel opened and connected.");

            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);

            String fileName = localFile;
            File f = new File(fileName);
            channelSftp.put(new FileInputStream(f), f.getName());

            System.out.println("File Transferred Successfully to the Host.");

        } catch (Exception ex) {

            System.out.println("Exception Found while Transfer the Response.");

        } finally {

            channelSftp.exit();
            System.out.println("SFTP Channel Exited.");

            channel.disconnect();
            System.out.println("Channel Disconnected.");

            session.disconnect();
            System.out.println("Host Session Disconnected.");

        }

    }


    @SneakyThrows
    public static void waitUntilSTIBORecordsImport() {
        Thread.sleep(STIBO_IMPORT_WAIT);
        LoggerUtil.logINFO("Waiting for STIBO data import..");
    }

}
