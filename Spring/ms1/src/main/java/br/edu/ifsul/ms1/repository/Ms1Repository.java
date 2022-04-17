package br.edu.ifsul.ms1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsul.ms1.model.Ms1;

@Repository
public interface Ms1Repository extends JpaRepository<Ms1, Integer> {
    
}
