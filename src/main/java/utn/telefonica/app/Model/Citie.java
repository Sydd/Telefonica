package utn.telefonica.app.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Citie {

    private int id_city;  //Entity
    private String city_name;
    private int line_prefix;
    private int id_state;
}
