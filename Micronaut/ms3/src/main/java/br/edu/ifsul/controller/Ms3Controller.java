package br.edu.ifsul.controller;

import java.util.List;
import java.util.Optional;

import br.edu.ifsul.model.dto.Ms3Dto;
import br.edu.ifsul.service.Ms3Service;
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

@Controller("/api/ms3")
public class Ms3Controller {

    @Inject
    private Ms3Service service;

    @Get("/status")
    public String statusServico(@Value("${local.server.port}") String port) {
        return String.format("Service up and running on port %s", port);
    }
    
    @Post(
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON
    )
    public Ms3Dto create(@Body Ms3Dto ms3) {
        return service.create(ms3);
    }
    
    @Get
    public List<Ms3Dto> findAll() {
        List<Ms3Dto> dtos = service.findAll();
        return dtos;
    }
    
    @Get("/{id}")
    public Ms3Dto findById(@PathVariable Integer id) {
        Optional<Ms3Dto> ms3 = service.findById(id);
        return ms3.get();
    }

    @Put
    public Ms3Dto update(@Body Ms3Dto ms3) {
        ms3 = service.update(ms3);
        return ms3;
    }

    @Delete("/{id}")
    public void remove(@PathVariable Integer id) {
        service.removeMs3(id);
    }
    
}