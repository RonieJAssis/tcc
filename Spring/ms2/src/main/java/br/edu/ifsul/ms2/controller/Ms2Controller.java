package br.edu.ifsul.ms2.controller;

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

import br.edu.ifsul.ms2.model.dto.Ms2Dto;
import br.edu.ifsul.ms2.service.Ms2Service;

@RestController
@RequestMapping("/api/ms2")
public class Ms2Controller {
    @Autowired
    private Ms2Service service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String port) {
        return String.format("Service up and running on port %s", port);
    }
    
    @PostMapping
    public ResponseEntity<Ms2Dto> create(@RequestBody Ms2Dto ms2) {
        ms2 = service.create(ms2);
        return new ResponseEntity<>(ms2, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Ms2Dto>> findAll() {
        List<Ms2Dto> dtos = service.findAll();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<Ms2Dto> findById(@PathVariable Integer id) {
        Optional<Ms2Dto> ms2 = service.findById(id);

        if(ms2.isPresent()) {
            return new ResponseEntity<>(ms2.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Ms2Dto> update(@RequestBody Ms2Dto ms2) {
        ms2 = service.update(ms2);

        return new ResponseEntity<>(ms2, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> remove(@PathVariable Integer id) {
        service.removeMs2(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping(value="/{ms1}/list")
    public ResponseEntity<List<Ms2Dto>> findByMs1(@PathVariable Integer ms1) {
        List<Ms2Dto> dtos = service.findByMs1(ms1);

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
