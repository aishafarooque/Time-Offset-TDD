package timedifference;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeDifference {
  private TimeDifferenceWebService timeDifferenceWebService;

  public TimeDifference (TimeDifferenceWebService aWebService) { timeDifferenceWebService = aWebService; }

  public String getDifference(String cityNames) throws RuntimeException {
    return Stream.of(cityNames.split(" "))
      .map(word -> word + " " + timeDifferenceWebService.calculateDifference(word))
      .collect(Collectors.joining("\n"));
  }
}
