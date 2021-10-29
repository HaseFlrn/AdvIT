import java.io.*;

public class MyFile {

  private static final String PATH = "Textfiles/";

  public static String read(String filename, int line_no) throws IOException {
    BufferedReader fileIn = new BufferedReader(new FileReader(PATH + filename));
    String line = null;
    line_no -= 1;

    for (int i = 0; i < line_no; i++) {
      fileIn.readLine();
    }
    line = fileIn.readLine();

    fileIn.close();
    return line;
  }

  private static int getLastLine(String filename) throws IOException {
    BufferedReader fileIn = new BufferedReader(new FileReader(PATH + filename));
    int ctr = 0;
    while (fileIn.readLine() != null) {
      ctr++;
      fileIn.readLine();
      ctr++;
    }
    fileIn.close();
    return ctr--;
  }

  public static void write(String filename, int line_no, String data)
    throws IOException {
    line_no--;
    String[] fileData = new String[(getLastLine(filename) > line_no)
      ? getLastLine(filename)
      : line_no];
    BufferedReader read = new BufferedReader(new FileReader(PATH + filename));

    for (int i = 0; i < fileData.length; i++) {
      if (i == line_no) {
        fileData[i] = data;
        read.readLine();
      } else {
        fileData[i] = read.readLine();
      }
    }
    read.close();
    PrintWriter fileOut = new PrintWriter(new FileWriter(PATH + filename));
    for (String line : fileData) {
      fileOut.println(line);
    }
    fileOut.flush();
    fileOut.close();
  }
}
