package endtoend.hello

import base.GoalTrackerAbstractAcceptanceTest
import javax.ws.rs.core.Response
import org.junit.Test
import static org.junit.Assert.*

public class HealthCheckAT extends GoalTrackerAbstractAcceptanceTest {

    private static final String HEALTH_CHECK = "hello, world!"

    @Test
    public void testHealthCheck() {
        Response response = gt.health.healthCheck()
        assertEquals(response.status, 200)
        assertEquals(response.readEntity(String.class), HEALTH_CHECK)
    }
}