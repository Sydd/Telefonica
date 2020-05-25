package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rates")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rate")
    private int id;
    @Column(name = "cost")
    private float cost;
    @Column(name = "price_per_minute")
    private float pricePerMinute;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private City cities;
/*
  @OneToMany(mappedBy = "rate")
    private List<Call> calls;/*/
}
