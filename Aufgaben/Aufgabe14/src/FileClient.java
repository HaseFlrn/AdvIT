import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class FileClient {

  static final int SERVER_PORT = 5999;
  static final String EXIT_STRING = "exit";

  public static void main(String[] args) {
    int port = SERVER_PORT;
    DatagramSocket socket = null;
    byte[] buffer = new byte[65507];

    try {
      socket = new DatagramSocket();
      System.out.println("Client is active");
      BufferedReader userIn = new BufferedReader(
        new InputStreamReader(System.in)
      );

      while (true) {
        try {
          String line = userIn.readLine();
          if (line.equals(EXIT_STRING)) {
            break;
          }
          InetAddress serverAddress = InetAddress.getByName("localhost");
          byte[] message = line.getBytes();
          DatagramPacket packet = new DatagramPacket(
            message,
            message.length,
            serverAddress,
            port
          );
          socket.send(packet);

          DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
          socket.receive(dp);
          System.out.println(new String(dp.getData(), 0, dp.getLength()));
        } catch (UnknownHostException e) {
          e.printStackTrace();
        } catch (IOException e) {
          System.err.println(e);
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    } finally {
      if (socket != null) {
        socket.close();
      }
    }
  }
}
