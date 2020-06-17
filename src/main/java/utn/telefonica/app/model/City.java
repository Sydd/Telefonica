package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "cities")
public class City{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_city")
    private int id;  //Entity
    @Column(name = "cityName", unique = true)
    private String cityName;
    @Column(name = "linePrefix")
    private int linePrefix;

    @Column(name ="cost_per_minute")
    private float costPerMinute;

    @Column(name ="price_per_minute")
    private float pricePerMinute;

    @OneToMany(mappedBy = "city")
    private List<User> userList;

    //EL MappedBy lleva el nombre del atributo en la clase referenciada. En nuestor caso CityTo.
    @OneToMany(mappedBy = "cityTo")
    @JsonIgnore
    private List<Call> callsFrom;

    //EL MappedBy lleva el nombre del atributo en la clase referenciada. En nuestor caso CityFrom.
    @OneToMany(mappedBy = "cityFrom")
    @JsonIgnore
    private List<Call> callsTo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private State state;
}
