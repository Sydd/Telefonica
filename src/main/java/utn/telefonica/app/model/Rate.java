package utn.telefonica.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rate")
    private int id; //Entity

    @Column(name = "id_city_origin")
    private City cityOrigin;

    @Column(name = "id_city_destination")
    private City cityDestination;

    @Column(name = "cost_per_minute")
    private float costPerMinute;

    @Column(name = "price_per_minute")
    private float pricePerMinute;

}
