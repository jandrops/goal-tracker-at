package client

import org.glassfish.jersey.client.JerseyClient


class AbstractClient extends JerseyClient{
    protected static final String baseApiUrl = "http://localhost:8080"
}

