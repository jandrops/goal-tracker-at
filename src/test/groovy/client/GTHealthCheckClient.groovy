package client

import javax.ws.rs.core.Response

class GTHealthCheckClient extends AbstractClient {

    Response healthCheck() {
        target(baseApiUrl).path("hello").request().get()
    }

}
