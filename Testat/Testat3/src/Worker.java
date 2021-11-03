import java.net.*;

public class Worker extends Thread {

  private String dpData;
  private String wdir = FileServer.getWdir();
  private DatagramSocket ds = null;
  private DatagramPacket dp = null;
  private String command;
  private WorkerMonitor workerMonitor;

  public Worker(
    String dpData,
    DatagramSocket ds,
    DatagramPacket dp,
    WorkerMonitor workerMonitor
  ) {
    this.ds = ds;
    this.dp = dp;
    this.dpData = dpData;
    this.command = dpData.split(" ", 2)[0];
    this.workerMonitor = workerMonitor;
  }

  @Override
  public void run() {
    String filename = "";
    String newData = "";
    int lineNo = -1;
    MyFile f = null;
    String param[] = null;
    String param2[] = null;
    String answer = "∗∗∗ ERROR 900: unknown error";

    DatagramPacket dp2 = null;
    try {
      if (command.equals("READ")) {
        try {
          workerMonitor.startReading();
          param = dpData.split(" ", 2);
          param2 = param[1].split(",", 2);
          filename = param2[0].trim();
          lineNo = Integer.parseInt(param2[1].trim());
          f = new MyFile(wdir + filename);
          answer = f.read(lineNo);
          sleep(2000);
          workerMonitor.stopReading();
        } catch (Exception e) {
          answer = "*** ERROR 901: bad READ command";
          throw new Exception(e);
        } // catch
      } else if (command.equals("WRITE")) {
        try {
          workerMonitor.startWriting();
          param = dpData.split(" ", 2);
          param2 = param[1].split(",", 3);
          filename = param2[0].trim();
          lineNo = Integer.parseInt(param2[1].trim());
          newData = param2[2];
          f = new MyFile(wdir + filename);
          answer = f.write(lineNo, newData);
          workerMonitor.stopWriting();
        } catch (Exception e) {
          answer = "*** ERROR 901: bad WRITE command";
          throw new Exception(e);
        } // catch
      } else {
        answer = "*** ERROR 902: unknown command";
        throw new Exception("Unknown Command");
      }
      try {
        dp2 =
          new DatagramPacket(
            answer.getBytes(),
            answer.getBytes().length,
            dp.getAddress(),
            dp.getPort()
          );
        ds.send(dp2);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
