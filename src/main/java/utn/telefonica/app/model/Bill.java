package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bills")
public class Bill{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "totalCalls")
    private int total_calls;
    @Column(name = "costPrice")
    private int cost_price;
    @Column(name = "totalPrice")
    private int total_price;
    @Column(name = "createdAt")
    private java.sql.Timestamp created_at;
    @Column(name = "dueDate")
    private java.sql.Timestamp due_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Line line;

}
