package com.goodput;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Receiver {

    private final static int SIZE = 1400;

    public static void startUDP(int port) throws IOException {

        byte[] data = new byte[SIZE];
        DatagramSocket socket = new DatagramSocket(port);
        int packetCounter = 0;
        socket.setSoTimeout(30000);
        int packetID = 0;
        boolean transmitting = true;
        long start = System.currentTimeMillis();
        long end = 0;
        long totalReceived = 0;

        while(transmitting) {
            DatagramPacket packet = new DatagramPacket(data, data.length);
            end = System.currentTimeMillis();
            try {
                socket.receive(packet);
                packetCounter++;
                totalReceived += packet.getData().length;
            }
            catch(SocketTimeoutException timeOut) {
                transmitting = false;
            }
            packetID = ByteBuffer.wrap(Arrays.copyOfRange(packet.getData(), 0, 4)).getInt();
            String content = new String(packet.getData(), 4, (packet.getLength()-4));
        }
        socket.close();
        System.out.println("Received packets:\t"+packetCounter);
        System.out.println("Average throughput:\t"+ Receiver.calculateThroughput(start, end, totalReceived)+" kbit/s");

    }

    public static int calculateThroughput(long start, long end, long receivedBytes) {
        long duration = end - start;
        return Receiver.calculateThroughput(duration, receivedBytes);
    }

    public static int calculateThroughput(long duration, long receivedBytes) {
        int durationInSec = (int) duration / 1000;
        int data_kbit = (int) (receivedBytes*8) / 1000;
        int throughput = data_kbit / durationInSec;
        return throughput;
    }

    public static void main(String... args) throws IOException {
        Receiver.startUDP(15999);
    }

}
