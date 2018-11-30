package com.goodput;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

public class UDPClient {

    public static void main(String... args) throws IOException, InterruptedException {

        InetAddress address = InetAddress.getByName("localhost");

        while(true) {

            String time = new Date().toString();
            byte[] timeRaw = time.getBytes();



            IDataPacket packet = new IDataPacket(timeRaw, "localhost", 9998);

            DatagramSocket dSocket = new DatagramSocket();

            dSocket.send(packet.getDatagramPacket());

            System.out.println("Packet sent!");

            Thread.sleep(1000);
        }

    }
}
