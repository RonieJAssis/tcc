package br.edu.ifsul.ms3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsul.ms3.model.Ms3;

@Repository
public interface Ms3Repository extends JpaRepository<Ms3, Integer> {
    
}

