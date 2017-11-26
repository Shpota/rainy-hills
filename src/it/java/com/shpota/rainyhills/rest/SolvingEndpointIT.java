package com.shpota.rainyhills.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;
import org.wildfly.swarm.undertow.WARArchive;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.net.URI;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.wildfly.swarm.arquillian.DefaultDeployment.Type.WAR;

@RunWith(Arquillian.class)
@DefaultDeployment(type = WAR)
public class SolvingEndpointIT {

    @ArquillianResource
    private URI uri;

    @Deployment
    public static WARArchive createDeployment() throws Exception {
        return ShrinkWrap.create(WARArchive.class);
    }

    @Test
    public void shouldGetWaterVolume() {
        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(uri)
                .path("/api/calculate-water-volume")
                .queryParam("hills", "7", "1", "8")
                .request();

        Response response = request.get();

        assertThat(response.getStatusInfo(), is(OK));
        assertThat(response.getMediaType(), is(TEXT_PLAIN_TYPE));
        assertThat(response.readEntity(String.class), is("6"));
    }
}
