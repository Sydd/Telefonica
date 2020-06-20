package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "total_calls")
    private int totalCalls;
    @Column(name = "cost_price")
    private int costPrice;
    @Column(name = "total_price")
    private int totalPrice;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "due_date")
    private Date dueDate; //CAMBIE SQL DATE POR java DATE PORQUE SE SUPONE QUE NO TIENE QEU SER DE SQL O MYSQL O LO QUE SEA.

    @Column(name = "is_payed")
    private Boolean isPayed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference(value = "phoneline_reference") //cuando tenemos mas de un jsobackrefence  hay que ponerle value.
    private PhoneLine phoneLine;



}
