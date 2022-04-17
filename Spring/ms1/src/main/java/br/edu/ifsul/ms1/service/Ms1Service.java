package br.edu.ifsul.ms1.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsul.ms1.http.Ms2FeignClient;
import br.edu.ifsul.ms1.model.Ms1;
import br.edu.ifsul.ms1.model.dto.Ms1Dto;
import br.edu.ifsul.ms1.model.dto.Ms2Dto;
import br.edu.ifsul.ms1.repository.Ms1Repository;

@Service
public class Ms1Service {

    @Autowired
    private Ms1Repository repository;

    @Autowired
    private Ms2FeignClient ms2Client;

    public Ms1Dto create(Ms1Dto ms1) {
        ModelMapper mapper = new ModelMapper();
        Ms1 ms1Entidade = mapper.map(ms1, Ms1.class);
        ms1Entidade.setDtInsert(Calendar.getInstance());
        ms1Entidade = repository.save(ms1Entidade);
        return mapper.map(ms1Entidade, Ms1Dto.class);
    }

    public Ms1Dto update(Ms1Dto ms1) {
        ModelMapper mapper = new ModelMapper();
        Ms1 ms1Entidade = mapper.map(ms1, Ms1.class);
        ms1Entidade = repository.save(ms1Entidade);
        return mapper.map(ms1Entidade, Ms1Dto.class);
    }

    public List<Ms1Dto> findAll() {
        List<Ms1> ms1s = repository.findAll();
        return ms1s.stream()
            .map(ms1 -> new ModelMapper().map(ms1, Ms1Dto.class))
            .collect(Collectors.toList());
    }

    public Optional<Ms1Dto> findById(Integer id) {
       Optional<Ms1> ms1 = repository.findById(id);

       if(ms1.isPresent()) {

            Ms1Dto dto = new ModelMapper().map(ms1.get(), Ms1Dto.class);

            List<Ms2Dto> ms2s = ms2Client.FindByMs1(id);
            dto.setMs2s(ms2s);

            return Optional.of(dto);
       }

       return Optional.empty();
    }

    public void removeMs1(Integer id) {
        repository.deleteById(id);
    }
}
