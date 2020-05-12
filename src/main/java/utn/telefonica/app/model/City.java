package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Data
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_city")
    private int id;  //Entity

    @Column(name = "city_name", unique = true)
    private String cityName;

    private int line_prefix;
    private int id_state;

    @OneToMany(mappedBy = "city")
    private List<Costumer> costumers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private State state;
}
