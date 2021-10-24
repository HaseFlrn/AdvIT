import java.io.*;
import java.net.*;
import java.util.UUID; //Klasse, die unique Ids erstellt anhand des System-Seeds

public class MsgServer {

  public static final int DEFAULT_PORT = 7777;
  // TODO: edit to your respective path
  public static final String PATH_TO_DESTINATION =
    "/home/florian/Desktop/Messages/";

  public static boolean createAndWrite(String messageValue, String key) {
    FileWriter writer = null;
    File newFile = null;
    //Erstelle Datei
    try {
      newFile = new File(PATH_TO_DESTINATION + key);
      if (newFile.createNewFile()) {
        //Speichere Nachricht
        try {
          writer = new FileWriter(PATH_TO_DESTINATION + key);
          writer.write(messageValue);
          writer.close();
          return true;
        } catch (IOException e) {
          System.err.println(e);
        }
      }
    } catch (IOException e) {
      System.err.println(e);
    }
    return false;
  } //createAndWirte

  public static String readFromFile(String key) {
    BufferedReader fileReader = null;
    String message = null;
    try {
      fileReader =
        new BufferedReader(new FileReader(PATH_TO_DESTINATION + key));
      try {
        message = fileReader.readLine();
        fileReader.close();
      } catch (IOException e) {
        System.err.println(e);
        return message;
      }
    } catch (FileNotFoundException e) {
      System.err.println(e);
    }
    return message;
  } //readFromFile

  public static void main(String[] args) {
    int port = DEFAULT_PORT;
    BufferedReader networkIn = null;
    PrintWriter networkOut = null;
    Socket connection = null;
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
          if (splittedLine[0].equals("SAVE")) {
            String messageValue = splittedLine[1];
            String key = UUID.randomUUID().toString(); //generiert unique-ID
            if (createAndWrite(messageValue, key)) {
              networkOut.println("KEY " + key); // sendet KEY und Schlüssel an Client
              networkOut.flush();
              networkOut.close();
            }
          } else if (splittedLine[0].equals("GET")) {
            String key = splittedLine[1];
            String messageValue = readFromFile(key);
            if (messageValue != null) {
              networkOut.println("OK " + messageValue); // sendet OK und Schlüssel an Client
              networkOut.flush();
              networkOut.close();
            } else {
              networkOut.println("FAILED "); // sendet OK und Schlüssel an Client
              networkOut.flush();
              networkOut.close();
            }
          } else {
            networkOut.println("FAILED "); // sendet OK und Schlüssel an Client
            networkOut.flush();
            networkOut.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          try {
            if (connection != null) connection.close();
          } catch (IOException e) {
            System.err.println(e);
          }
        }
      }
    } catch (IOException e) {
      System.err.println(e);
    }
  } //main
}
