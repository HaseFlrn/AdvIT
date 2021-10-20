import java.io.*;
import java.net.*;
import java.util.UUID;

public class MsgServer {

  public static final int DEFAULT_PORT = 7777;
  public static final String PATH_TO_DESKTOP = "~/Desktop/Messages/";

  public static void main(String[] args) {
    int port = DEFAULT_PORT;
    BufferedReader networkIn = null;
    PrintWriter networkOut = null;
    Socket connection = null;
    FileWriter writer = null;
    try {
      ServerSocket server = new ServerSocket(port);
      while (true) {
        try {
          connection = server.accept();
          networkIn =
            new BufferedReader(
              new InputStreamReader(connection.getInputStream())
            );
          networkOut = new PrintWriter(connection.getOutputStream());
          String line = networkIn.readLine();
          String[] splittedLine = line.split(" ", 2); //trennt das erste Wort des Strings vom Rest
          System.out.println(splittedLine[0] + " und " + splittedLine[1]);
          if (splittedLine[0].equals("SAVE")) {
            String value = splittedLine[1];
            String key = UUID.randomUUID().toString(); //generiert unique-ID
            //Erstelle Datei
            try {
              File newFile = new File(PATH_TO_DESKTOP + key);
              if (newFile.createNewFile()) {
                networkOut.println("KEY " + key); // sendet OK und Schlüssel an Client
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
            //Speichere Nachricht
            try {
              writer = new FileWriter(PATH_TO_DESKTOP + key);
              writer.write(value);
              writer.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          try {
            if (connection != null) connection.close();
          } catch (IOException e) {}
        }
      }
    } catch (IOException e) {
      System.err.println(e);
    }
  }
}
