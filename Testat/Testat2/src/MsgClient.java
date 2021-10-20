import java.io.*;
import java.net.*;

public class MsgClient {

  public static final int SERVER_PORT = 7777;

  public static void main(String[] args) {
    String hostname = "localhost";
    PrintWriter networkOut = null;
    BufferedReader networkIn = null;
    Socket socket = null;
    try {
      socket = new Socket(hostname, SERVER_PORT);
      System.out.println("Connected to server");
      networkIn =
        new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedReader userIn = new BufferedReader(
        new InputStreamReader(System.in)
      );
      networkOut = new PrintWriter(socket.getOutputStream());

      //Read and send message
      System.out.println(
        "Please write 'SAVE value' and hit enter to send your message(value)."
      );
      System.out.println(
        "Please write 'GET key' and hit enter to get your requested message."
      );
      String theLine = userIn.readLine();
      networkOut.println(theLine);
      networkOut.flush();
      System.out.println(networkIn.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (socket != null) try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
