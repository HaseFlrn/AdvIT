package Aufgabe13;

import java.io.*;
import java.net.*;

public class ChatClient {

  public static int DEFAULT_PORT = 4998;
  public static String[] addresses = { "localhost", "255.255.255.255" };

  public static void main(String[] args) {
    DatagramSocket socket;
    InetAddress dest;
    byte[] data;
    BufferedReader userIn;

    try {
      socket = new DatagramSocket();
      dest =
        InetAddress.getByName(
          (DEFAULT_PORT == 4998) ? addresses[1] : addresses[0]
        );

      userIn = new BufferedReader(new InputStreamReader(System.in));
      data = userIn.readLine().getBytes();

      DatagramPacket packet = new DatagramPacket(
        data,
        data.length,
        dest,
        DEFAULT_PORT
      );
      socket.send(packet);
      //   packet = new DatagramPacket(data, data.length);
      //   socket.receive(packet);
      //   System.out.println(new String(packet.getData(), 0, packet.getLength()));
    } catch (SocketException e) {
      System.err.println(e);
    } catch (UnknownHostException e) {
      System.err.println(e);
    } catch (IOException e) {}
  }
}
