import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class FileServer {

  public static final int DEFAULT_PORT = 5999;

  public static void main(String[] args) {
    DatagramSocket socket = null;
    byte[] buffer = new byte[65507];

    try {
      socket = new DatagramSocket(DEFAULT_PORT);
      System.out.println("Server is active");

      while (true) {
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        try {
          socket.receive(dp);
          int port = dp.getPort();
          String ip = dp.getAddress().getHostName();
          String data = new String(dp.getData(), 0, dp.getLength());

          String[] splittedLine = data.split(" ", 2);
          if (splittedLine[0].equals("READ")) {
            splittedLine = splittedLine[1].split(",", 2);
            String filename = splittedLine[0];
            int line_no = Integer.parseInt(splittedLine[1]);

            byte[] read = MyFile.read(filename, line_no).getBytes();
            dp =
              new DatagramPacket(
                read,
                read.length,
                InetAddress.getByName(ip),
                port
              );
            socket.send(dp);
          } else if (splittedLine[0].equals("WRITE")) {
            String[] newsplittedLine = splittedLine[1].split(",", 3);

            String filename = newsplittedLine[0];
            int line_no = Integer.parseInt(newsplittedLine[1]);
            String writeData = newsplittedLine[2];

            MyFile.write(filename, line_no, writeData);
          }
        } catch (IOException e) {
          e.printStackTrace();
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
