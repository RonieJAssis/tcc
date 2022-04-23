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

import br.edu.ifsul.model.Ms2;
import br.edu.ifsul.model.dto.Ms2Dto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("/api/ms2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Ms2Resource {
    
    @ConfigProperty(name = "quarkus.http.port")
    Integer port;

    @GET
    @Path("/status")
    public String statusServico() {
        return String.format("Service up and running on port %s", port);
    }

    @POST
    @Transactional
    public Response create(Ms2Dto ms2) {
        ModelMapper mapper = new ModelMapper();
        Ms2 ms2Entidade = mapper.map(ms2, Ms2.class);
        ms2Entidade.setDtInsert(Calendar.getInstance());
        ms2Entidade.persist();
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(ms2).build();
    }
    
    @GET
    public Response findAll() {
        PanacheQuery<Ms2> ms2s = Ms2.findAll();
        List<Ms2Dto> ms2Dto = ms2s.list().stream()
            .map(ms2 -> new ModelMapper().map(ms2, Ms2Dto.class))
            .collect(Collectors.toList());
        return Response.ok(ms2Dto).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById( @PathParam("id") Integer id) {
        Ms2 ms2 = Ms2.findById(id);

        if(ms2 != null) {
            Ms2Dto ms2Dto = new ModelMapper().map(ms2, Ms2Dto.class);
            return Response.ok(ms2Dto).build();
        }

        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
    }

    @PUT
    @Transactional
    public Response update(Ms2Dto ms2) {
        Ms2 ms2Update = Ms2.findById(ms2.getId());
        ms2Update.setName(ms2.getName());
        ms2Update.setValue(ms2.getValue());
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(ms2).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response remove(@PathParam("id") Integer id) {
        Ms2 ms2 = Ms2.findById(id);
        if(ms2!=null){
            ms2.delete();
        }
        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
    } 

    @GET
    @Path("/{ms1}/list")
    public Response findByMs1(@PathParam("ms1") Integer ms1) {
        PanacheQuery<Ms2> ms2s = Ms2.find("ms1",ms1);

        if(ms2s.list().isEmpty()){
            return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        }
        
        List<Ms2Dto> ms2Dto = ms2s.list().stream()
            .map(ms2 -> new ModelMapper().map(ms2, Ms2Dto.class))
            .collect(Collectors.toList());
        return Response.ok(ms2Dto).build();
    }
}
