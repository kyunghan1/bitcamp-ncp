package bitcamp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CSVReader {

  public static void main(String[] args) {

    String filePath = "\"C:\\Users\\KyungHan\\Desktop\\csv.csv\"";

    File file = null;
    BufferedWriter bw = null;
    String NEWLINE = System.lineSeparator();

    try {

      file = new File(filePath);
      bw = new BufferedWriter(new FileWriter(file));

      bw.write("번호,제목,내용,암호,조회수,등록일");
      bw.write (NEWLINE) ;

      bw.write("1,가,나,1111,10,2023-01-23");
      bw.write("\n");

      bw.write("2,다,라,2222,11,2023-01-31");
      bw.write("\n");

      bw. flush();
      bw. close();

    } catch (Exception e) {
      e.printStackTrace();

    }
  }
}

