
package com.goodput;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;

public class FileReceiver {

    private final static int SIZE = 1400;

    // UDP Server -------------------------------------------
    public static void startUDP(int port) throws IOException {
        byte[] data = new byte[SIZE];
        DatagramSocket socket = new DatagramSocket(port);
        int packetCounter = 0;
        socket.setSoTimeout(10000);
        int packetID = 0;
        boolean receiving = true;
        long totalReceived = 0;
        long start = System.currentTimeMillis();
        long  end= 0;

        while(receiving) {
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
                ++packetCounter;
                packetID = FileReceiver.getPacketID(packet);
                totalReceived += packet.getData().length;
            }
            catch(SocketTimeoutException timeOut) {
                receiving = false;
            }
        }
        end = System.currentTimeMillis() - 10000;
        socket.close();
        FileReceiver.printStats(packetID, FileReceiver.calculateThroughput(start, end, totalReceived), packetCounter);
    }


    public static void startTCP(int port) throws IOException {
        long start = 0;
        ServerSocket server = new ServerSocket(port);
        int packetID;
        int packCounter = 0;
        long totalReceived = 0;
        byte[] data = new byte[1400];
        byte[] packID = new byte[4];

        System.out.println("Conntection testing....");
        Socket connection = server.accept();
        connection.setSoTimeout(5000);
        DataInputStream input = new DataInputStream(connection.getInputStream());
        System.out.println("Connection started");

                packetID = input.read(data, 0, 1400);

                while (packetID != -1) {
                    if (start == 0) {
                        start = System.currentTimeMillis();
                    }
                    packCounter++;
                    packetID = input.read(data, 0, 1400);
                    totalReceived += data.length;
                }

        long end = System.currentTimeMillis();
        FileReceiver.printStats(packCounter, FileReceiver.calculateThroughput(start, end, totalReceived), packCounter);
    }


    private static void printStats(int packetID, float throughput, int packCounter) {
        System.out.println("Packet-ID of last received packet:\t\t"+packetID +
                "\n\nAverage throughput:\t\t"+ throughput +" kbit/s"+
                "\nPackets received:\t\t"+packCounter+
                "\nPackets lost:\t\t\t"+(packetID-packCounter)+
                "\n-------------------------------------");
    }


    private static int getPacketID(DatagramPacket packet) {
        return ByteBuffer.wrap(Arrays.copyOfRange(packet.getData(), 0, 4)).getInt();
    }


    private static int getPacketIDFromBytes(byte[] packet) {
        return ByteBuffer.wrap(Arrays.copyOfRange(packet, 0, 4)).getInt();
    }


    private static float calculateThroughput(long start, long end, long receivedBytes) {
        long duration = end - start;
        return FileReceiver.calculateThroughput(duration, receivedBytes);
    }


    private static float calculateThroughput(long duration, long receivedBytes) {
        float div = 1000;
        float durationInSec = duration / div;
        float data_kbit = (receivedBytes*8) / div;
        return data_kbit / durationInSec;
    }

    public static void main(String... args) throws IOException{

        Scanner scan = new Scanner(System.in);

        System.out.println("Want to start UDP or TCP?");
        String start = scan.next();

        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex) {

        }
        if(start.startsWith("u")) {
            System.out.println("Receiving UDP...");
            FileReceiver.startUDP(18999);
        }
        else {
            System.out.println("Receiving TCP...");
            FileReceiver.startTCP(18999);
        }

    }

}
