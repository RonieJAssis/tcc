package br.edu.ifsul.ms1.http;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifsul.ms1.model.dto.Ms2Dto;

@FeignClient(name = "controller-ms2")
public interface Ms2FeignClient {

    @GetMapping(path = "/api/ms2/{ms1}/list")
    List<Ms2Dto> FindByMs1(@PathVariable Integer ms1);

}
