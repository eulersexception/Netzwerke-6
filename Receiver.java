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
            packetID = Receiver.getPacketID(packet);
        }
        socket.close();

        System.out.println("Average throughput:\t"+ Receiver.calculateThroughput(start, end, totalReceived)+" kbit/s");
        System.out.println("Packets received:\t"+packetCounter+"\nPackets lost:\t"+(packetCounter-packetID+1));
    }

    public static int getPacketID(DatagramPacket packet) {
        return ByteBuffer.wrap(Arrays.copyOfRange(packet.getData(), 0, 4)).getInt();
    }

    public static long calculateThroughput(long start, long end, long receivedBytes) {
        long duration = end - start;
        return Receiver.calculateThroughput(duration, receivedBytes);
    }

    public static long calculateThroughput(long duration, long receivedBytes) {
        long durationInSec = duration / 1000;
        long data_kbit = (receivedBytes*8) / 1000;
        long throughput = data_kbit / durationInSec;
        return throughput;
    }

    public static void main(String... args) throws IOException {
        Receiver.startUDP(15999);
    }

}
