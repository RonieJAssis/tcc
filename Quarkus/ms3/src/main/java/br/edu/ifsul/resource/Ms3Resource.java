package br.edu.ifsul.resource;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.modelmapper.ModelMapper;

import br.edu.ifsul.model.Ms3;
import br.edu.ifsul.model.dto.Ms3Dto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("/api/ms3")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Ms3Resource { 

    @ConfigProperty(name = "quarkus.http.port")
    Integer port;

    @GET
    @Path("/status")
    public String statusServico() {
        return String.format("Service up and running on port %s", port);
    }

    @POST
    @Transactional
    public Response create(Ms3Dto ms3) {
        ModelMapper mapper = new ModelMapper();
        Ms3 ms3Entidade = mapper.map(ms3, Ms3.class);
        ms3Entidade.setDtInsert(Calendar.getInstance());
        ms3Entidade.persist();
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(ms3).build();
    }
    
    @GET
    public Response findAll() {
        PanacheQuery<Ms3> ms3s = Ms3.findAll();
        List<Ms3Dto> ms3Dto = ms3s.list().stream()
            .map(ms3 -> new ModelMapper().map(ms3, Ms3Dto.class))
            .collect(Collectors.toList());
        return Response.ok(ms3Dto).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById( @PathParam("id") Integer id) {
        Ms3 ms3 = Ms3.findById(id);

        if(ms3 != null) {
            Ms3Dto ms3Dto = new ModelMapper().map(ms3, Ms3Dto.class);
            return Response.ok(ms3Dto).build();
        }

        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
    }

    @PUT
    @Transactional
    public Response update(Ms3Dto ms3) {
        Ms3 ms3Update = Ms3.findById(ms3.getId());
        ms3Update.setName(ms3.getName());
        ms3Update.setValue(ms3.getValue());
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(ms3).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response remove(@PathParam("id") Integer id) {
        Ms3 ms3 = Ms3.findById(id);
        if(ms3!=null){
            ms3.delete();
        }
        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
    } 
}
