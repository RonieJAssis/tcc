package br.edu.ifsul.ms3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Ms3Dto {
    private Integer id;
    private String name;
    private Double value;
}
