package br.edu.ifsul.http;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.edu.ifsul.model.dto.Ms2Dto;

@Path("/api/ms2")
@RegisterRestClient()
public interface Ms2Service {

    @GET
    @Path("/{ms1}/list")
    public List<Ms2Dto> findByMs1(@PathParam("ms1") Integer ms1);

}
