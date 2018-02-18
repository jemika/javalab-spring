package lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COUNTRY")
public class SimpleCountry implements Country {
    @Id
    @GeneratedValue
    @Wither
    private Long id;
    private String name;
    private String codeName;
}