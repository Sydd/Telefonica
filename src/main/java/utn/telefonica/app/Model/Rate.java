package utn.telefonica.app.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rate {

    private int id_rate; //Entity
    private int id_city_origin;
    private int id_city_destiny;
    private float cost;
    private float price_per_minute;

}
