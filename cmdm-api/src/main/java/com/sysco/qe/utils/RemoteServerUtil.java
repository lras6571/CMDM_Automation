package com.sysco.qe.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.File;
import java.io.FileInputStream;

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

        System.out.println("preparing the host information for sftp.");

        try {

            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            System.out.println("Host connected.");

            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("sftp channel opened and connected.");

            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);

            String fileName = localFile;
            File f = new File(fileName);
            channelSftp.put(new FileInputStream(f), f.getName());

            System.out.println("File transfered successfully to host.");

        } catch (Exception ex) {

            System.out.println("Exception found while tranfer the response.");

        } finally {

            channelSftp.exit();
            System.out.println("sftp Channel exited.");

            channel.disconnect();
            System.out.println("Channel disconnected.");

            session.disconnect();
            System.out.println("Host Session disconnected.");

        }

    }

}
