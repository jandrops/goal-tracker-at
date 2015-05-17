package endtoend.goal.add

import base.GoalTrackerAbstractAcceptanceTest
import com.theothermattm.goal.domain.Goal

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status
import org.junit.Test


import static org.junit.Assert.*;

public class GoalAddAT extends GoalTrackerAbstractAcceptanceTest {

    // Positive Tests

    @Test
    public void testAddGoal() {
        Goal goal = buildRandomGoalModel()
        Response response = gt.goal.addGoal(goal)
        // This should return 201 instead of 200
        assertEquals(response.status, Status.OK.statusCode)
        List<Goal> respGoals = responseModel(response)
        assert respGoals[respGoals.size()-1].id != null
        assert goal.equals(respGoals[respGoals.size()-1])
    }

    @Test
    public void testAddGoalSameGoalTwiceDifferentId() {
        Goal goal = buildRandomGoalModel()

        Response response = gt.goal.addGoal(goal)
        assertEquals(response.status, Status.OK.statusCode)

        List<Goal> respGoals = responseModel(response)

        response = gt.goal.addGoal(goal)
        assertEquals(response.status, Status.OK.statusCode)

        List<Goal> respGoals2 = responseModel(response)

        assert respGoals[respGoals.size()-1].id != respGoals2[respGoals2.size()-1].id
    }

    // Negative tests

    @Test
    public void testAddGoalNullName() {
        Goal goal = buildGoalModel(null, new Date(), "notes", 9)

        Response response = gt.goal.addGoal(goal)
        // This should return 400
        assertEquals(response.status, Status.OK.statusCode)

        List<Goal> respGoals = responseModel(response)
        assert goal.equals(respGoals[respGoals.size()-1])
    }

    @Test
    public void testAddGoalNotAcceptableAcceptHeader() {
        Goal goal = buildRandomGoalModel()

        Response response = gt.goal.addGoal(goal, MediaType.APPLICATION_XML)
        assertEquals(response.status, Status.OK.statusCode)
    }

    // Other tests

    @Test
    public void testAddGoalSpecialCharacters() {
        Goal goal = buildGoalModel(" \",{\"£\":£%}", new Date(), "\n\",{££%}", 9)

        Response response = gt.goal.addGoal(goal)
        // This should return 201 of 200
        assertEquals(response.status, Status.OK.statusCode)

        List<Goal> respGoals = responseModel(response)
        assert goal.equals(respGoals[respGoals.size()-1])
    }

    @Test
    public void testAdd500Goals() {
        Goal goal

        Response response = gt.goal.getGoals()
        assertEquals(response.status, Status.OK.statusCode)

        List<Goal> respGoals = responseModel(response)
        int startSize = respGoals.size()
        println startSize

        for (int i = 0; i < 500; i++) {

            goal = buildRandomGoalModel()

            response = gt.goal.addGoal(goal)
            assertEquals(response.status, Status.OK.statusCode)

            respGoals = responseModel(response)
            assert respGoals.size() == startSize + i + 1
            assert goal.equals(respGoals[respGoals.size()-1])
        }
    }

    private Goal buildGoalModel(String name, Date date, String notes, int weight) {
        Goal goalModel = new Goal(name)
        goalModel.setDueDate(date)
        goalModel.setNotes(notes)
        goalModel.setWeight(weight)
        goalModel
    }


}