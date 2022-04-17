package br.edu.ifsul.ms1.model;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ms1 {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ms1_sequence")
    @SequenceGenerator(name="ms1_sequence", sequenceName="ms1_seq")
    private Integer id;
    private String name;
    private Double value;
    private Calendar dtInsert;  
}
