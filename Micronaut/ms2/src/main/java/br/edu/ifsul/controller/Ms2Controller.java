package br.edu.ifsul.controller;

import java.util.List;
import java.util.Optional;

import br.edu.ifsul.model.dto.Ms2Dto;
import br.edu.ifsul.service.Ms2Service;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

@Controller("/api/ms2")
public class Ms2Controller {

    @Inject
    private Ms2Service service;

    @Get("/status")
    public String statusServico(@Value("${local.server.port}") String port) {
        return String.format("Service up and running on port %s", port);
    }
    
    @Post(
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON
    )
    public Ms2Dto create(@Body Ms2Dto ms2) {
        return service.create(ms2);
    }
    
    @Get
    public List<Ms2Dto> findAll() {
        List<Ms2Dto> dtos = service.findAll();
        return dtos;
    }
    
    @Get("/{id}")
    public Ms2Dto findById(@PathVariable Integer id) {
        Optional<Ms2Dto> ms2 = service.findById(id);
        return ms2.get();
    }

    @Put
    public Ms2Dto update(@Body Ms2Dto ms2) {
        ms2 = service.update(ms2);
        return ms2;
    }

    @Delete("/{id}")
    public void remove(@PathVariable Integer id) {
        service.removeMs2(id);
    }
    
    @Get("/{ms1}/list")
    public List<Ms2Dto> findByMs1(@PathVariable Integer ms1) {
        List<Ms2Dto> dtos = service.findByMs1(ms1);
        return dtos;
    }
}
