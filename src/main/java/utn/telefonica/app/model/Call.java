package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    private PhoneLine phoneLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "city_to")
   // @JoinColumn(name = "id_city_to")
    private City cityTo;

    //SI TENES MAS DE UN JSONBACKREFERENCE TENES QUE PONERLE DISTINTO NOMBRE CON VALUE.

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "city_from")
    private City cityFrom;

    @Column (name = "date_call")
    private Date callDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "phoneline_destiny")
    private PhoneLine phoneLineDestiny;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "customer")
    private Customer customer;
/*/
  /*  @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Rate rate;*/
}
