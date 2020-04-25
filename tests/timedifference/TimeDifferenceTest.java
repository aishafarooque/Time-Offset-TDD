package timedifference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TimeDifferenceTest {
  private TimeDifference timeDifference;
  private TimeDifferenceWebService timeDifferenceWebService;


  @BeforeEach
  void init() {
    timeDifferenceWebService = mock(TimeDifferenceWebService.class);

    when(timeDifferenceWebService.calculateDifference(anyString())).thenReturn("");

    timeDifference = new TimeDifference(timeDifferenceWebService);
  }

  @Test
  void canary() {
    assertTrue(true);
  }

  @Test
  void timeDifferenceEmptyString() { assertEquals(" ", timeDifference.getDifference("")); }

  @Test
  void timeDifferenceOneCorrectCity() {
    when(timeDifferenceWebService.calculateDifference("Riga")).thenReturn("2");

    assertEquals("Riga 2", timeDifference.getDifference("Riga"));
  }

  @Test
  void timeifferenceOneWrongCity() {
    when(timeDifferenceWebService.calculateDifference("Prais")).thenReturn("---");

    assertEquals("Prais ---", timeDifference.getDifference("Prais"));
  }

  @Test
  void timeDifferenceTwoCorrectCities() {
    when(timeDifferenceWebService.calculateDifference("Riga")).thenReturn("2");
    when(timeDifferenceWebService.calculateDifference("Paris")).thenReturn("0");

    assertEquals("Riga 2\nParis 0", timeDifference.getDifference("Riga Paris"));
  }

  @Test
  void timeDifferenceTwoIncorrectCities() {
    when(timeDifferenceWebService.calculateDifference("Rgia")).thenReturn("---");
    when(timeDifferenceWebService.calculateDifference("Prais")).thenReturn("---");

    assertEquals("Rgia ---\nPrais ---", timeDifference.getDifference("Rgia Prais"));
  }

  @Test
  void timeDifferenceOneWrongOneRightCity() {
    when(timeDifferenceWebService.calculateDifference("Riga")).thenReturn("2");
    when(timeDifferenceWebService.calculateDifference("Prais")).thenReturn("---");

    assertEquals("Riga 2\nPrais ---", timeDifference.getDifference("Riga Prais"));
  }
}
