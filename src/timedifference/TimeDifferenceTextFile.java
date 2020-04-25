package timedifference;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TimeDifferenceTextFile {
  public static void main(String[] args) {
    var timeDifference = new TimeDifferenceWorldTimeAPI();
    var fileTimeDifference = new TimeDifference(timeDifference);

    try {
      Files.lines(Paths.get("src/timedifference/sample.txt"))
        .map(fileTimeDifference::getDifference)
        .forEach(System.out::println);
    } catch (Exception e) {
      System.out.println("ERR");
    }
  }
}
