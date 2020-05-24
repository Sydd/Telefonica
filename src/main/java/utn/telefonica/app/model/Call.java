package utn.telefonica.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue
    @Column(name = "id_call")
    private int id;

    @Column(name = "id_line_from")
    private Line lineFrom;

    @Column(name = "id_line_to")
    private Line lineTo;

    @Column(name = "id_city_from")
    private City cityFrom;

    @Column(name = "id_city_to")
    private City cityTo;

    @Column(name = "id_rate")
    private Rate rate;

    @Column(name = "call_duration")
    private float call_duration;

    @Column(name = "total_price")
    private float total_price;

    @Column(name = "id_bill")
    private Bill bill;


}
