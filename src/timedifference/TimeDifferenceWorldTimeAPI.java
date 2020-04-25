package timedifference;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TimeDifferenceWorldTimeAPI implements TimeDifferenceWebService {

  @Override
  public String calculateDifference(String cityName) {
    try {
      String offset = getCityOffset(cityName);
      return (offset == "---" ? "---" : String.valueOf(Integer.parseInt(offset)/3600));
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  protected String getCityOffset(String cityName) throws IOException {
    String link = "http://worldtimeapi.org/api/timezone/Europe/" + cityName + ".txt", line;
    URL url = new URL(String.format(link));

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    if (conn.getResponseCode() != 200) { return "---"; }

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");

    DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

    while ((line = bufferedReader.readLine()) != null) {
      if (line.startsWith("raw_offset: ")) {
        break;
      }
    }

    return line.split(":")[1].trim();
  }
}
