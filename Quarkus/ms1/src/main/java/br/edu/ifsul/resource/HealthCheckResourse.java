package br.edu.ifsul.resource;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HealthCheckResourse {
    
    @GET
    @Path("/health")
    public Response health() {
        return Response.ok(Map.of("STATUS", "UP")).build();
    }

    @GET
    @Path("/status")
    public Response status() {
        return Response.ok(Map.of()).build();
    }

}
