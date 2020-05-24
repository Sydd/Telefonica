package utn.telefonica.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {

    @Id
    @GeneratedValue
    @Column(name = "id_bill")
    private int id;

    @Column(name = "id_customer")
    private Customer customer;  // Aca tal vez podriamos hacer que traiga de la bd todo el customer completo si es que nos sirve
    @Column(name = "id_line")
    private Line line;

    @Column (name = "total_calls")
    private int totalCalls;
    @Column (name = "total_calls")
    private int costPrice;
    @Column (name = "total_calls")
    private int finalPrice;
    @Column (name = "total_calls")
    private Date created_at;
    private Date due_date;
}
