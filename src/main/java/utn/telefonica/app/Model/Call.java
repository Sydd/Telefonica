package utn.telefonica.app.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Call {

    private int id_call;
    private int id_line_from;
    private int ld_line_to;
    private int id_city_from;
    private int id_city_to;
    private int id_rate;
    private float call_duration;
    private float total_price;
    private int id_bill;
}
