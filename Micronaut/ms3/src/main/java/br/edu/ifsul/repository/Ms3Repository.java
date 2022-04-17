package br.edu.ifsul.repository;

import java.util.List;

import br.edu.ifsul.model.Ms3;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface Ms3Repository extends CrudRepository<Ms3, Integer>{

    @Override
    List<Ms3> findAll();
    
}
