package br.edu.ifsul.repository;

import java.util.List;

import br.edu.ifsul.model.Ms1;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface Ms1Repository extends CrudRepository<Ms1, Integer>{

    @Override
    List<Ms1> findAll();
    
}