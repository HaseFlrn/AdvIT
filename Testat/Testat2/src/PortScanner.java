import java.io.*;
import java.net.*;

public class PortScanner {

  public static void main(String[] args) {
    String host = "localhost";
    for (int i = 1; i < 7778; i++) {
      try {
        Socket s = new Socket(host, i);
        System.out.println("There is a server on port " + i + " at " + host);
        s.close();
      } catch (UnknownHostException e) {
        System.err.println(e);
        break;
      } catch (IOException e) {} // skip , probably no s e r v e r wa i ti ng here
    } // end f o r
  } // end main
} // end LowPortScanner
