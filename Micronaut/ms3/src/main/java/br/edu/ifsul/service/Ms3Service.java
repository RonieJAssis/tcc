package br.edu.ifsul.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.edu.ifsul.model.Ms3;
import br.edu.ifsul.model.dto.Ms3Dto;
import br.edu.ifsul.repository.Ms3Repository;
import jakarta.inject.Inject;

public class Ms3Service {
    @Inject
    private Ms3Repository repository;

    public Ms3Dto create(Ms3Dto ms3) {
        ModelMapper mapper = new ModelMapper();
        Ms3 ms3Entidade = mapper.map(ms3, Ms3.class);
        ms3Entidade.setDtInsert(Calendar.getInstance());
        ms3Entidade = repository.save(ms3Entidade);
        return mapper.map(ms3Entidade, Ms3Dto.class);
    }

    public Ms3Dto update(Ms3Dto ms3) {
        ModelMapper mapper = new ModelMapper();
        Ms3 ms3Entidade = mapper.map(ms3, Ms3.class);
        ms3Entidade = repository.save(ms3Entidade);
        return mapper.map(ms3Entidade, Ms3Dto.class);
    }

    public List<Ms3Dto> findAll() {
        List<Ms3> ms3s = repository.findAll();
        return ms3s.stream()
            .map(ms3 -> new ModelMapper().map(ms3, Ms3Dto.class))
            .collect(Collectors.toList());
    }

    public Optional<Ms3Dto> findById(Integer id) {
       Optional<Ms3> ms3 = repository.findById(id);

       if(ms3.isPresent()) {

            Ms3Dto dto = new ModelMapper().map(ms3.get(), Ms3Dto.class);
            return Optional.of(dto);
       }

       return Optional.empty();
    }

    public void removeMs3(Integer id) {
        repository.deleteById(id);
    } 
}
