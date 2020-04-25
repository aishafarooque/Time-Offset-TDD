package timedifference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

public class WebServiceTimeDifferenceTest {
  private TimeDifferenceWebService timeDifferenceWorldTimeAPI;

  @BeforeEach
  void init() { timeDifferenceWorldTimeAPI = new TimeDifferenceWorldTimeAPI(); }

  @Test
  void timeDifferenceForValidCity() {
    assertEquals("1", timeDifferenceWorldTimeAPI.calculateDifference("Paris"));
  }

  @Test
  void timeDifferenceInvalidCity() {
    assertEquals("---", timeDifferenceWorldTimeAPI.calculateDifference("Prais"));
  }

  @Test
  void getTimeDifferenceWhenNetworkError() throws Exception {
    var spy = Mockito.spy(new TimeDifferenceWorldTimeAPI());

    doThrow(new java.net.ConnectException("ERR")).when(spy).getCityOffset(anyString());

    var exception = assertThrows(RuntimeException.class, () -> spy.calculateDifference("Paris"));

    assertEquals("ERR", exception.getMessage());
  }
}
