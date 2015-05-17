package base

import client.GoalTrackerClient
import com.theothermattm.goal.domain.Goal
import org.apache.commons.lang.RandomStringUtils
import org.codehaus.jackson.map.ObjectMapper

import javax.ws.rs.core.Response

class GoalTrackerAbstractAcceptanceTest {
    protected static GoalTrackerClient gt = new GoalTrackerClient()
    private ObjectMapper mapper = new ObjectMapper()

    protected List<Goal> responseModel (Response response) {
        String resp = response.readEntity(String.class)
        mapper.readValue(resp, mapper.getTypeFactory().constructCollectionType(List.class, Goal.class))
    }

    protected Goal buildRandomGoalModel() {
        String random = RandomStringUtils.randomNumeric(14)
        Goal goalModel = new Goal(random)
        goalModel.setDueDate(new Date())
        goalModel.setNotes("notes for ${random}")
        goalModel.setWeight(new Random().nextInt(1000)-500)
        println goalModel.name
        goalModel
    }

    protected void assertGoal(Goal goal) {
        assert goal.id
        // Commenting assert goal.name != null as currently it is allowed
//        assert goal.name
    }
}
