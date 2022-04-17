package br.edu.ifsul.ms2.model;

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
public class Ms2 {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ms2_sequence")
    @SequenceGenerator(name="ms2_sequence", sequenceName="ms2_seq")
    private Integer id;
    private Integer ms1;
    private String name;
    private Double value;
    private Calendar dtInsert;
}
