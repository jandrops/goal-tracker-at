package endtoend.goals.get

import base.GoalTrackerAbstractAcceptanceTest
import com.theothermattm.goal.domain.Goal

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status
import org.junit.Test
import static org.junit.Assert.*

public class GoalsGetAT extends GoalTrackerAbstractAcceptanceTest {

    @Test
    public void testGetGoals() {
        Goal goal = buildRandomGoalModel()

        Response response = gt.goal.addGoal(goal)
        assertEquals(response.status, Status.OK.statusCode)

        response = gt.goal.getGoals()
        assertEquals(response.status, Status.OK.statusCode)
        List<Goal> respGoals = responseModel(response)
        assert goal.equals(respGoals[respGoals.size()-1])

        for (int i = 0; i < respGoals.size(); i++) {
          assertGoal(respGoals[i])
        }
    }

    // Negative test

    @Test
    public void testGetGoalsNotAcceptableAcceptHeader() {
        Goal goal = buildRandomGoalModel()

        Response response = gt.goal.addGoal(goal)
        assertEquals(response.status, Status.OK.statusCode)

        response = gt.goal.getGoals(MediaType.APPLICATION_XML)
        assertEquals(response.status, Status.NOT_ACCEPTABLE.statusCode)
    }
}