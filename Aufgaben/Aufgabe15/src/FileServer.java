/** Sequenzieller FileServer
READ / WRITE
hp 12−sep −2013
USES : MyFile.java
**/
import java.net.*;

public class FileServer {

  public static final int MAXSIZE = 65507;
  private static int port = 5999;
  private static String wdir =
    System.getProperty("user.home") + "/Desktop/FileServer/";

  public static String getWdir() {
    return wdir;
  }

  public static void main(String[] args) {
    // declare variables first:
    String dpData = "";
    DatagramSocket ds = null;
    DatagramPacket dp = null;
    // set up se rve r
    try { // 1
      ds = new DatagramSocket(port);
      System.out.println("Server successfully started on port" + port);
      dp = new DatagramPacket(new byte[MAXSIZE], MAXSIZE);
      while (true) { // wait for and process client requests
        try { // 2
          ds.receive(dp);
          dpData = new String(dp.getData(), 0, dp.getLength()).trim();
          Worker worker = new Worker(dpData, ds, dp);
          worker.start();
        } catch (Exception e) { // try 2
          e.printStackTrace();
        }
        // send back result or error message

      } //while
    } catch (Exception e) {
      e.printStackTrace();
    }
  } //main
} // cla s s
