import java.io.*;
import java.nio.file.Files;

public class MyFile {

  private File filename;

  public MyFile(String fullPath) {
    this.filename = new File(fullPath);
    try {
      this.filename.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String read(int line_no) throws IOException {
    BufferedReader fileIn = new BufferedReader(new FileReader(filename));
    String line = null;
    line_no -= 1;

    for (int i = 0; i < line_no; i++) {
      fileIn.readLine();
    }
    line = fileIn.readLine();

    fileIn.close();
    return line;
  }

  private int getLastLine() throws IOException {
    BufferedReader fileIn = new BufferedReader(new FileReader(filename));
    int ctr = 0;
    while (fileIn.readLine() != null) {
      ctr++;
      fileIn.readLine();
      ctr++;
    }
    fileIn.close();
    return ctr--;
  }

  public String write(int line_no, String data) throws IOException {
    line_no--;
    String answer = "** Couldn't write into the File";
    String[] fileData = new String[(getLastLine() > line_no)
      ? getLastLine()
      : line_no + 1];
    BufferedReader read = new BufferedReader(new FileReader(filename));

    for (int i = 0; i < fileData.length; i++) {
      if (i == line_no) {
        fileData[i] = data;
        read.readLine();
      } else {
        String result = read.readLine();
        fileData[i] = result == null ? " " : result;
      }
    }

    read.close();
    PrintWriter fileOut = new PrintWriter(new FileWriter(filename));
    for (String line : fileData) {
      fileOut.println(line);
    }
    answer = "** Success";
    fileOut.flush();
    fileOut.close();
    return answer;
  }
}
