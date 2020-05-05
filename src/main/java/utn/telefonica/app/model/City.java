package utn.telefonica.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class City {

    private int id_city;  //Entity
    private String city_name;
    private int line_prefix;
    private int id_state;
}
