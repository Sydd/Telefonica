
package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.telefonica.app.dto.CallDto;

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
    private int callDuration;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "total_cost")
    private float totalCost;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private PhoneLine phoneLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "city_to")
    private City cityTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "city_from")
    private City cityFrom;

    @Column (name = "date_call")
    private Date callDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "phoneline_destiny")
    private PhoneLine phoneLineDestiny;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value="call_bill")
    private Bill bill;

}

