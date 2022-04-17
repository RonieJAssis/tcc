package br.edu.ifsul.ms1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsul.ms1.model.dto.Ms1Dto;
import br.edu.ifsul.ms1.service.Ms1Service;

@RestController
@RequestMapping("/api/ms1")
public class Ms1Controller {
    @Autowired
    private Ms1Service service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String port) {
        return String.format("Service up and running on port %s", port);
    }
    

    @PostMapping
    public ResponseEntity<Ms1Dto> create(@RequestBody Ms1Dto ms1) {
        ms1 = service.create(ms1);
        return new ResponseEntity<>(ms1, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Ms1Dto>> findAll() {
        List<Ms1Dto> dtos = service.findAll();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<Ms1Dto> findById(@PathVariable Integer id) {
        Optional<Ms1Dto> ms1 = service.findById(id);

        if(ms1.isPresent()) {
            return new ResponseEntity<>(ms1.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Ms1Dto> update(@RequestBody Ms1Dto ms1) {
        ms1 = service.update(ms1);

        return new ResponseEntity<>(ms1, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> remove(@PathVariable Integer id) {
        service.removeMs1(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}
