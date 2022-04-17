package br.edu.ifsul.ms2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsul.ms2.model.Ms2;

@Repository
public interface Ms2Repository extends JpaRepository<Ms2, Integer> {
    List<Ms2> findByMs1(Integer ms1);
}