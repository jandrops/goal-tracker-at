package client

import com.theothermattm.goal.domain.Goal

import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


class GTGoalClient extends AbstractClient {


    Response addGoal(Goal goal, String accept = MediaType.APPLICATION_JSON) {
        target(baseApiUrl).path("services/goal").request(accept).post(Entity.entity(goal, MediaType.APPLICATION_JSON))
    }

    Response getGoals(String accept = MediaType.APPLICATION_JSON) {
        target(baseApiUrl).path("services/goals").request(accept).get()
    }

}
