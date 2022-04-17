package br.edu.ifsul.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ms2Dto {
    private Integer id;
    private Integer ms1;
    private String name;
    private Double value;
}

