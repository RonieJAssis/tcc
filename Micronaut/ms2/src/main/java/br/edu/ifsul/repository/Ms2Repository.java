package br.edu.ifsul.repository;

import java.util.List;

import br.edu.ifsul.model.Ms2;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface Ms2Repository extends CrudRepository<Ms2, Integer>{
    
    @Override
    List<Ms2> findAll();

    List<Ms2> findByMs1(Integer ms1);

}
