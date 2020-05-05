package utn.telefonica.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {

    private int id_bill; // Entity
    private int id_Costumer;  // Aca tal vez podriamos hacer que traiga de la bd todo el customer completo si es que nos sirve
    private int id_line;
    private int total_calls;
    private int cost_price;
    private int total_price;
    private Date created_at;
    private Date due_date;
}
