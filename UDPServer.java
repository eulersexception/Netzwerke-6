package com.goodput;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;

public class UDPServer {

    public static void main(String... args) {

        try(DatagramSocket socket = new DatagramSocket(9998)) {

            DatagramPacket packet = new DatagramPacket(new byte[1400], 1400);
            socket.receive(packet);

            InetAddress source = packet.getAddress();
            int port = packet.getPort();
            int length = packet.getLength();
            byte[] data = packet.getData();

            System.out.printf("Anfrage von %s vom Port %d mit der Laenge %d:%n%s%n",
                    source, port, length, new String(data, 0, length));


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
