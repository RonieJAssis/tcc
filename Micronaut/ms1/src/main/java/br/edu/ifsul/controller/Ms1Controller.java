package br.edu.ifsul.controller;

import java.util.List;
import java.util.Optional;

import br.edu.ifsul.model.dto.Ms1Dto;
import br.edu.ifsul.service.Ms1Service;
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

@Controller("/api/ms1")
public class Ms1Controller {
    
    @Inject
    private Ms1Service service;

    @Get("/status")
        public String statusServico(@Value("${local.server.port}") String port) {
        return String.format("Service up and running on port %s", port);
    }
    
    @Post(
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON
    )
    public Ms1Dto create(@Body Ms1Dto ms1) {
        return service.create(ms1);
    }
    
    @Get
    public List<Ms1Dto> findAll() {
        List<Ms1Dto> dtos = service.findAll();
        return dtos;
    }
    
    @Get("/{id}")
    public Ms1Dto findById(@PathVariable Integer id) {
        Optional<Ms1Dto> ms1 = service.findById(id);
        if(ms1.isPresent()){
            return ms1.get();
        }else{
            return null;
        }
        
    }

    @Put
    public Ms1Dto update(@Body Ms1Dto ms1) {
        ms1 = service.update(ms1);
        return ms1;
    }

    @Delete("/{id}")
    public void remove(@PathVariable Integer id) {
        service.removeMs1(id);
    }
}
