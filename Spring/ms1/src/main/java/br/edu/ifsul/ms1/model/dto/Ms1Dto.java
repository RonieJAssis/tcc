package br.edu.ifsul.ms1.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ms1Dto {
    private Integer id;
    private String name;
    private Double value;
    private List<Ms2Dto> ms2s;
}
