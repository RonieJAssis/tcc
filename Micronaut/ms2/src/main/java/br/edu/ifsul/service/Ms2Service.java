package br.edu.ifsul.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.edu.ifsul.model.Ms2;
import br.edu.ifsul.model.dto.Ms2Dto;
import br.edu.ifsul.repository.Ms2Repository;
import jakarta.inject.Inject;

public class Ms2Service {
    
    @Inject
    private Ms2Repository repository;

    public Ms2Dto create(Ms2Dto ms2) {
        ModelMapper mapper = new ModelMapper();
        Ms2 ms2Entidade = mapper.map(ms2, Ms2.class);
        ms2Entidade.setDtInsert(Calendar.getInstance());
        ms2Entidade = repository.save(ms2Entidade);
        return mapper.map(ms2Entidade, Ms2Dto.class);
    }

    public Ms2Dto update(Ms2Dto ms2) {
        ModelMapper mapper = new ModelMapper();
        Ms2 ms2Entidade = mapper.map(ms2, Ms2.class);
        ms2Entidade = repository.update(ms2Entidade);
        return mapper.map(ms2Entidade, Ms2Dto.class);
    }

    public List<Ms2Dto> findAll() {
        List<Ms2> ms2s = repository.findAll();
        return ms2s.stream()
            .map(ms2 -> new ModelMapper().map(ms2, Ms2Dto.class))
            .collect(Collectors.toList());
    }

    public Optional<Ms2Dto> findById(Integer id) {
       Optional<Ms2> ms2 = repository.findById(id);

       if(ms2.isPresent()) {

            Ms2Dto dto = new ModelMapper().map(ms2.get(), Ms2Dto.class);

            return Optional.of(dto);
       }

       return Optional.empty();
    }

    public void removeMs2(Integer id) {
        repository.deleteById(id);
    }
    
    public List<Ms2Dto> findByMs1(Integer Ms1) {
        List<Ms2> ms2s = repository.findByMs1(Ms1);

        return ms2s.stream()
            .map(ms2 -> new ModelMapper().map(ms2, Ms2Dto.class))
            .collect(Collectors.toList());
    }
}
