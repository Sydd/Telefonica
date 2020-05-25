package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "city_name", unique = true)
    private String cityName;
    @Column(name = "line_prefix")
    private int linePrefix;

    @OneToMany(mappedBy = "city")
    private List<Customer> customers;

    //EL MappedBy lleva el nombre del atributo en la clase referenciada. En nuestor caso CityTo.
    @OneToMany(mappedBy = "cityTo")
    @JsonIgnore
    private List<Call> callsFrom;

    //EL MappedBy lleva el nombre del atributo en la clase referenciada. En nuestor caso CityFrom.
    @OneToMany(mappedBy = "cityFrom")
    @JsonIgnore
    private List<Call> callsTo;

  // @OneToMany(mappedBy = "city")
  //private List<Rate> rate;*/


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private State state;
}
