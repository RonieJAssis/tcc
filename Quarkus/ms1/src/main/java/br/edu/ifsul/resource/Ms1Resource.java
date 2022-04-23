package br.edu.ifsul.resource;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.modelmapper.ModelMapper;

import br.edu.ifsul.http.Ms2Service;
import br.edu.ifsul.model.Ms1;
import br.edu.ifsul.model.dto.Ms1Dto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

public class Ms1Resource {
    
    @ConfigProperty(name = "quarkus.http.port")
    Integer port;

    @Inject
    @RestClient
    Ms2Service ms2Service;

    @GET
    @Path("/status")
    public String statusServico() {
        return String.format("Service up and running on port %s", port);
    }

    @POST
    @Transactional
    public Response create(Ms1Dto ms1) {
        ModelMapper mapper = new ModelMapper();
        Ms1 ms1Entidade = mapper.map(ms1, Ms1.class);
        ms1Entidade.setDtInsert(Calendar.getInstance());
        ms1Entidade.persist();
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(ms1).build();
    }
    
    @GET
    public Response findAll() {
        PanacheQuery<Ms1> ms1s = Ms1.findAll();
        List<Ms1Dto> ms1Dto = ms1s.list().stream()
            .map(ms1 -> new ModelMapper().map(ms1, Ms1Dto.class))
            .collect(Collectors.toList());
        return Response.ok(ms1Dto).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findById( @PathParam("id") Integer id) {
        Ms1 ms1 = Ms1.findById(id);

        if(ms1 != null) {
            Ms1Dto ms1Dto = new ModelMapper().map(ms1, Ms1Dto.class);
            ms1Dto.setMs2s(ms2Service.findByMs1(ms1.getId()));
            return Response.ok(ms1).build();
        }

        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
    }

    @PUT
    @Transactional
    public Response update(Ms1Dto ms1) {
        Ms1 ms1Update = Ms1.findById(ms1.getId());
        ms1Update.setName(ms1.getName());
        ms1Update.setValue(ms1.getValue());
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(ms1).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response remove(@PathParam("id") Integer id) {
        Ms1 ms1 = Ms1.findById(id);
        if(ms1!=null){
            ms1.delete();
        }
        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
    } 

}
