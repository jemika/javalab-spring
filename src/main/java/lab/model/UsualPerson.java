package lab.model;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Component("person")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsualPerson implements Person {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private SimpleCountry country;
    private int age;
    private float height;
    private boolean programmer;
    @Wither
    private boolean broke;
    @OneToMany
    @Singular
    private List contacts;
}