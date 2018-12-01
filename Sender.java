package com.goodput;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Sender {

    private final static int SIZE = 1400;

    public static void sendViaUDP(String msg, String address, int port) throws IOException {
        byte[] message = msg.getBytes();
        int length = msg.getBytes().length;
        int packetNumber = 0;
        InetAddress ip = InetAddress.getByName(address);
        DatagramSocket socket = new DatagramSocket();
        byte[] packet = new byte[SIZE];

        for(int i = 0; i < length; i += (SIZE-4)) {
            // creating byte array with sequence number to check for packet loss
            byte[] packID = ByteBuffer.allocate(Integer.SIZE/8).putInt(packetNumber++).array();
            System.arraycopy(packID,0,packet,0, packID.length);
            System.arraycopy(message, i, packet, packID.length, Math.min((message.length-i),(SIZE-packID.length)));
            DatagramPacket messageChunk = new DatagramPacket(packet, packet.length, ip, port);
            socket.send(messageChunk);
        }
        socket.close();
    }

    public static int calculateThroughput(long start, long end, long receivedBytes) {
        long duration = end - start;
        return Sender.calculateThroughput(duration, receivedBytes);
    }

    public static int calculateThroughput(long duration, long receivedBytes) {
        int durationInSec = (int) duration / 1000;
        int data_kbit = (int) (receivedBytes*8) / 1000;
        int throughput = data_kbit / durationInSec;
        return throughput;
    }

    public static void main(String... args) throws IOException {

        String message = "Der Unterschied in den Konstruktoren liegt darin, an welche "+
                "Ports und Server die DatagramSocket-Objekte gebunden sind.\n"+
                " Für den Client ist dies nicht so interessant, da er häufig"+
                " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server,\n"+
                " kann dieser immer anhand der (---> ? <----)gespeicherten Adresse eine Rückantwort schicken."+
                "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket"+
                " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port\n"+
                " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung"+
                " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server"+
                " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur\n"+
                " dann wichtig, wenn hinter einer Firewall operiert wird."+
                "Der Unterschied in den Konstruktoren liegt darin, an welche"+
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
                " Der Unterschied in den Konstruktoren liegt darin, an welche"+
                "Der Unterschied in den Konstruktoren liegt darin, an welche "+
                "Ports und Server die DatagramSocket-Objekte gebunden sind."+
                " Für den Client ist dies nicht so interessant, da er häufig\n"+
                " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server,"+
                " kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken.\n"+
                "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket"+
                " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port\n"+
                " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung"+
                " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server"+
                " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur\n"+
                " dann wichtig, wenn hinter einer Firewall operiert wird."+
                "Der Unterschied in den Konstruktoren liegt darin, an welche(-----> ! <-------)"+
                "Der Unterschied in den Konstruktoren liegt darin, an welche "+
                "Ports und Server die DatagramSocket-Objekte gebunden sind.\n"+
                " Für den Client ist dies nicht so interessant, da er häufig"+
                " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server,"+
                " kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken."+
                "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket\n"+
                " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port"+
                " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung\n"+
                " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server"+
                " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur\n"+
                " dann wichtig, wenn hinter einer Firewall operiert wird."+
                "Der Unterschied in den Konstruktoren liegt darin, an welche"+
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
                " Der Unterschied in den Konstruktoren liegt darin, an welche"+
                "Der Unterschied in den Konstruktoren liegt darin, an welche\n "+
                "Ports und Server die DatagramSocket-Objekte gebunden sind."+
                " Für den Client ist dies nicht so interessant, da er häufig"+
                " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server,\n"+
                " kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken."+
                "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket"+
                " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port"+
                " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung\n"+
                " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server"+
                " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur"+
                " dann wichtig, wenn hinter einer Firewall operiert wird.\n"+
                "Der Unterschied in den Konstruktoren liegt darin, an welche"+"Der Unterschied in den Konstruktoren liegt darin, an welche "+
                "Ports und Server die DatagramSocket-Objekte gebunden sind."+
                " Für den Client ist dies nicht so interessant, da er häufig\n"+
                " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server,"+
                " kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken."+
                "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket"+
                " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port\n"+
                " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung"+
                " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server"+
                " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur"+
                " dann wichtig, wenn hinter einer Firewall operiert wird.\n"+
                "Der Unterschied in den Konstruktoren liegt darin, an welche"+
                "Der Unterschied in den Konstruktoren liegt darin, an welche " +
                " Ports und Server die DatagramSocket-Objekte gebunden sind." +
                "   Für den Client ist dies nicht so interessant, da er häufig\n" +
                " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server," +
                "  kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken." +
                "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket" +
                "  als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port\n" +
                "  nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung" +
                "  Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server" +
                "  seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur\n" +
                " dann wichtig, wenn hinter einer Firewall operiert wird." +
                " Der Unterschied in den Konstruktoren liegt darin, an welche"+
                "Der Unterschied in den Konstruktoren liegt darin, an welche \n"+
                "Ports und Server die DatagramSocket-Objekte gebunden sind."+
                " Für den Client ist dies nicht so interessant, da er häufig"+
                " als Absender einen beliebigen Port nutzen kann. Läuft ein Paket zum Server,"+
                " kann dieser immer anhand der gespeicherten Adresse eine Rückantwort schicken.\n"+
                "Wir werden das auch an den Beispielen sehen, in denen wir erst ein leeres Paket"+
                " als Anfrage schicken und dann den Server über uns informieren. Einen beliebigen Port"+
                " nimmt der erste Konstruktor, da dieser bedeutet, dass jeder Port zur Kommunikation in Richtung\n"+
                " Server verwendet werden kann. Nur ein Client muss wissen, auf welchem Port ein Server"+
                " seinen Dienst bereitstellt. Die Port-Adresse auf der Clientseite festzusetzen, ist nur\n"+
                " dann wichtig, wenn hinter einer Firewall operiert wird."+
                "Der Unterschied in den Konstruktoren liegt darin, an welche";


        long bytesSend = 0;
        long start = System.currentTimeMillis();

        while(System.currentTimeMillis()-start < 30000) {
            Sender.sendViaUDP(message, "localhost", 15999);
            bytesSend += message.getBytes().length;
        }

        System.out.println("Transmission Data: \nTotal time\t30 sec\nTotal data\t"+(bytesSend*8)+" bit\nThroughput\t"+Sender.calculateThroughput(30000, bytesSend)+" kbit/s");

    }
}
