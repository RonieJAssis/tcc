package br.edu.ifsul.http;

import java.util.List;

import br.edu.ifsul.model.dto.Ms2Dto;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;

@Client(id="controller-ms2",path = "/api/ms2")
public interface Ms2Client {
    
    @Get("/{ms1}/list")
    List<Ms2Dto> FindByMs1(@PathVariable Integer ms1);
}
