package br.edu.ifsul.http;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.edu.ifsul.model.dto.Ms2Dto;

@Path("/api/ms2")
@RegisterRestClient(baseUri="http://localhost:8011/controller-ms2")
public interface Ms2Service {

    @GET
    @Path("/{ms1}/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Ms2Dto> findByMs1(@PathParam("ms1") Integer ms1);

}
