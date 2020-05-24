package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "calls")
public class Call{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_call")
    private int id;
    @Column(name = "call_duration")
    private float callDuration;
    @Column(name = "total_price")
    private float totalPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Line lines;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "id_city_to")
    private City cityTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "id_city_from")
    private City cityFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Rate rate;
}
