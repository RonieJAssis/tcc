package br.edu.ifsul.ms3.controller;

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

import br.edu.ifsul.ms3.model.dto.Ms3Dto;
import br.edu.ifsul.ms3.service.Ms3Service;

@RestController
@RequestMapping("/api/ms3")
public class Ms3Controller {
    @Autowired
    private Ms3Service service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String port) {
        return String.format("Service up and running on port %s", port);
    }
    

    @PostMapping
    public ResponseEntity<Ms3Dto> create(@RequestBody Ms3Dto ms3) {
        ms3 = service.create(ms3);
        return new ResponseEntity<>(ms3, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Ms3Dto>> findAll() {
        List<Ms3Dto> dtos = service.findAll();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<Ms3Dto> findById(@PathVariable Integer id) {
        Optional<Ms3Dto> ms3 = service.findById(id);

        if(ms3.isPresent()) {
            return new ResponseEntity<>(ms3.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Ms3Dto> update(@RequestBody Ms3Dto ms3) {
        ms3 = service.update(ms3);

        return new ResponseEntity<>(ms3, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> remove(@PathVariable Integer id) {
        service.removeMs3(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}
