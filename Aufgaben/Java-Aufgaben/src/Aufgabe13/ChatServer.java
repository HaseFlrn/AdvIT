package Aufgabe13;

import java.io.*;
import java.net.*;

public class ChatServer {

  public static void main(String[] args) {
    DatagramSocket socket;
    byte[] buf = new byte[256];

    try {
      socket = new DatagramSocket(4998);
      while (true) {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        InetAddress address = packet.getAddress();
        // int port = packet.getPort();
        // packet = new DatagramPacket(buf, buf.length, address, port);
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println(address.toString() + ": " + received);
        // socket.send(packet);
      }
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (IOException e) {}
  }
}
