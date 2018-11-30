package com.goodput;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class IDataPacket {

    private static final int SIZE = 10240;
    private final byte[] data;
    private final int port;
    private static int packetCounter = 0;
    private int packetID = packetCounter++;
    private InetAddress ip;
    private final DatagramPacket datagramPacket;


    public IDataPacket(byte[] data, String target, int port) throws UnknownHostException {

        this.data = new byte[SIZE];
        System.arraycopy(data,0, this.data,0,SIZE);

        this.port = port;
        ip = InetAddress.getByName(target);

        datagramPacket = new DatagramPacket(this.data, SIZE, ip, port);
    }

    public DatagramPacket getDatagramPacket() {
        return datagramPacket;
    }
}
