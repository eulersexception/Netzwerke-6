package com.goodput;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class FileSender {

    private static String message = "Der Unterschied in den Konstruktoren liegt darin, an welche " +
            "Ports und Server die DatagramSocket-Objekte gebunden sind.\n" +
            " Für den Client ist dies nicht so interessant, da er häufig" +
            " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server,\n" +
            " kann dieser immer anhand der (---> ? <----)gespeicherten Adresse eine Rückantwort schicken." +
            "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket" +
            " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port\n" +
            " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung" +
            " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server" +
            " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur\n" +
            " dann wichtig, wenn hinter einer Firewall operiert wird." +
            "Der Unterschied in den Konstruktoren liegt darin, an welche" +
            "Der Unterschied in den Konstruktoren liegt darin, an welche\n" +
            "Ports und Server die DatagramSocket-Objekte gebunden sind" +
            "Für den Client ist dies nicht so interessant, da er häufig" +
            " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server\n" +
            "kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken." +
            "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket" +
            " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port\n" +
            " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung" +
            " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server\n" +
            "seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur" +
            "dann wichtig, wenn hinter einer Firewall operiert wird.\n" +
            " Der Unterschied in den Konstruktoren liegt darin, an welche" +
            "Der Unterschied in den Konstruktoren liegt darin, an welche " +
            "Ports und Server die DatagramSocket-Objekte gebunden sind." +
            " Für den Client ist dies nicht so interessant, da er häufig\n" +
            " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server," +
            " kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken.\n" +
            "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket" +
            " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port\n" +
            " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung" +
            " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server" +
            " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur\n" +
            " dann wichtig, wenn hinter einer Firewall operiert wird." +
            "Der Unterschied in den Konstruktoren liegt darin, an welche(-----> ! <-------)" +
            "Der Unterschied in den Konstruktoren liegt darin, an welche " +
            "Ports und Server die DatagramSocket-Objekte gebunden sind.\n" +
            " Für den Client ist dies nicht so interessant, da er häufig" +
            " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server," +
            " kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken." +
            "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket\n" +
            " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port" +
            " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung\n" +
            " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server" +
            " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur\n" +
            " dann wichtig, wenn hinter einer Firewall operiert wird." +
            "Der Unterschied in den Konstruktoren liegt darin, an welche" +
            "Der Unterschied in den Konstruktoren liegt darin, an welche\n" +
            " Ports und Server die DatagramSocket-Objekte gebunden sind." +
            " Für den Client ist dies nicht so interessant, da er häufig" +
            " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server," +
            " kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken\n" +
            " Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket" +
            "  als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port" +
            " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung\n" +
            " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Serve" +
            " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur\n" +
            " dann wichtig, wenn hinter einer Firewall operiert wird." +
            " Der Unterschied in den Konstruktoren liegt darin, an welche" +
            "Der Unterschied in den Konstruktoren liegt darin, an welche\n " +
            "Ports und Server die DatagramSocket-Objekte gebunden sind." +
            " Für den Client ist dies nicht so interessant, da er häufig" +
            " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server,\n" +
            " kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken." +
            "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket" +
            " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port" +
            " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung\n" +
            " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server" +
            " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur" +
            " dann wichtig, wenn hinter einer Firewall operiert wird.\n" +

            "Der Unterschied in den Konstruktoren liegt darin, an welche. Der Unterschied in den Konstruktoren liegt darin, an welche ";

    private final static int SIZE = 1400;

    public static void sendViaUDP(String msg, String address, int port, long delayInMilliSeconds, int flag) throws IOException, InterruptedException {
        byte[] message = msg.getBytes();
        int length = msg.getBytes().length;
        int packetNumber = 0;
        InetAddress ip = InetAddress.getByName(address);
        DatagramSocket socket = new DatagramSocket();
        byte[] packet;
        long bytesSent = 0;
        long duration = 20000;
        int currentIndex = 0;
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < duration) {
            packet = FileSender.createChunk(++packetNumber, message, currentIndex);
            DatagramPacket messageChunk = new DatagramPacket(packet, packet.length, ip, port);
            socket.send(messageChunk);
            bytesSent += messageChunk.getData().length;
            currentIndex =  (currentIndex + SIZE-4) % length;
            // this can throw an InterruptedException
            if (flag != 0 && packetNumber % flag == 0)
                Thread.sleep(delayInMilliSeconds);
        }
        duration = System.currentTimeMillis() - start;
        socket.close();
        FileSender.printStats(bytesSent, FileSender.calculateThroughput(duration, bytesSent), delayInMilliSeconds, flag, packetNumber);
    }


    public static void sendViaTCP(String msg, String address, int port, long delayMillis, int flag) throws IOException, InterruptedException {
        byte[] message = msg.getBytes();
        int length = msg.getBytes().length;
        //InetAddress ip = InetAddress.getByName(address);
        InetAddress ip = InetAddress.getByName(address);
        Socket socket = new Socket(ip, port);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        byte[] packet;
        int packetNumber = 0;
        long bytesSent = 0;
        long duration = 20000;
        int currentIndex = 0;
        long start = System.currentTimeMillis();;

        while (System.currentTimeMillis() - start < duration) {
            packet = FileSender.createChunk(++packetNumber, message, currentIndex);
            output.write(packet);
            output.flush();
            bytesSent += packet.length;
            currentIndex = (currentIndex + SIZE - 4) % length;

            // this can throw an InterruptedException
            if (flag != 0 && packetNumber % flag == 0)
                Thread.sleep(delayMillis);
        }
        duration = System.currentTimeMillis() - start;
        socket.close();
        System.out.println(bytesSent);

        FileSender.printStats(bytesSent, FileSender.calculateThroughput(duration, bytesSent), delayMillis, flag, packetNumber);
    }


    private static void printStats(long bytesSent, float throughput, long delay, int flag, int packets) {
        String expectedRate = "";
        if(delay == 0) {
            expectedRate = "Theoretically unlimited";
        }
        else
            expectedRate = Float.toString((1400*flag)*8/(float)delay);

        System.out.println("Transmission Data:\n" +
                "\nTotal data\t\t\t" + (bytesSent * 8) + " bit" +
                "\nThroughput\t\t\t" + throughput + " kbit/s" +
                "\nDelay time in ms\t" + delay +
                "\nDelay time per\t\t" + flag + " packets" +
                "\nEstimated rate\t\t" + expectedRate +
                "\nTotal amount of packets sent:\t" + packets +
                "\n-------------------------------");
    }


    private static byte[] createChunk(int id, byte[] src, int srcIndex) {
        byte[] packID = FileSender.createPackID(id);
        byte[] chunk = new byte[SIZE];
        System.arraycopy(packID,0, chunk, 0, packID.length);
        System.arraycopy(src, srcIndex, chunk, packID.length, Math.min((src.length-srcIndex), (SIZE-packID.length)));
        return chunk;
    }


    private static byte[] createPackID(int id) {
        return ByteBuffer.allocate(Integer.SIZE/8).putInt(id).array();
    }


    private static float calculateThroughput(long duration, long sentBytes) {
        long div = 1000;
        long durationInSec = duration / div;
        long data_kbit = (sentBytes * 8) / div;
        return data_kbit / (float) durationInSec;
    }


    public static void main(String... args) throws IOException, InterruptedException {

        Scanner scan = new Scanner(System.in);
        String protocol;
        String ip = "localhost";
        int port = 18999;
        long delayTime = 10;
        int packetFlag = 10;
        String repeatFlag = "y";

        while(repeatFlag.startsWith("y") || repeatFlag.startsWith("Y")) {
            System.out.println("To start program follow instructions.\nEnter protocol: ");

            protocol = scan.next();
            if (protocol.charAt(0) == 'U' || protocol.charAt(0) == 'u')
                protocol = "UDP";
            else
                protocol = "TCP";

            System.out.println("---------------------------------" +
                    "\nProgram parameters: " + protocol +
                    ", " + ip +
                    ", on port "+ port +
                    ", with " + delayTime +
                    " ms delay after each " + packetFlag + " packets. "+
                    "Enter 'Y' and press Enter to start!");

            if(scan.next().equalsIgnoreCase("y"));
            if (protocol.equals("UDP")) {
                System.out.println("UDP transmission starts now!");
                FileSender.sendViaUDP(message, ip, port, delayTime, packetFlag);
            }
            else {
                System.out.println("TCP transmission starts now!");
                FileSender.sendViaTCP(message, ip, port, delayTime, packetFlag);
            }

            System.out.println("Repeat program (y/n)?\n");
                repeatFlag = scan.next();

            if(repeatFlag.startsWith("y") || repeatFlag.startsWith("Y")) {

                System.out.println("Change parameters(y/n)?\n");
                if (scan.next().equalsIgnoreCase("y")) {

                    System.out.println("Delay time in ms: ");
                    delayTime = scan.nextLong();

                    System.out.println("Number of packets before delay: ");
                    packetFlag = scan.nextInt();

                    System.out.println("Portnumber: ");
                    port = scan.nextInt();
                }
            }

        }

    }
}
