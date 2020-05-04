package utn.telefonica.app.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Line {

    private int id_line; //Entity
    private int id_costumer;
    private String tipe;
    private String line_number;
}
