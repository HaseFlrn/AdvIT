import java.net.*;
import java.util.TreeMap;

public class FileServer {

  public static final int MAXSIZE = 65507;
  private static int port = 5999;
  private static String wdir =
    System.getProperty("user.home") + "/Desktop/FileServer/";

  private static TreeMap<String, WorkerMonitor> runningMonitor = new TreeMap<String, WorkerMonitor>();

  public static String getWdir() {
    return wdir;
  }

  private static WorkerMonitor getMonitor(String dpData) {
    String filename = dpData.split(" ", 2)[1].split(",", 2)[0];
    if (!runningMonitor.containsKey(filename)) {
      runningMonitor.put(filename, new WorkerMonitor());
    }
    return runningMonitor.get(filename);
  }

  public static void main(String[] args) {
    // declare variables first:
    String dpData = "";
    DatagramSocket ds = null;
    DatagramPacket dp = null;
    // set up server
    try { // 1
      ds = new DatagramSocket(port);
      System.out.println("Server successfully started on port" + port);
      while (true) { // wait for and process client requests
        try { // 2
          dp = new DatagramPacket(new byte[MAXSIZE], MAXSIZE);
          ds.receive(dp);
          dpData = new String(dp.getData(), 0, dp.getLength()).trim();
          Worker worker = new Worker(dpData, ds, dp, getMonitor(dpData));
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
