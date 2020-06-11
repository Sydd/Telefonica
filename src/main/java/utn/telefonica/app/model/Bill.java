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

    @Column(name = "totalCalls")
    private int total_calls;
    @Column(name = "costPrice")
    private int cost_price;
    @Column(name = "totalPrice")
    private int total_price;
    @Column(name = "createdAt")
    private Date created_at;
    @Column(name = "dueDate")
    private Date due_date; //CAMBIE SQL DATE POR java DATE PORQUE SE SUPONE QUE NO TIENE QEU SER DE SQL O MYSQL O LO QUE SEA. 

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference(value = "phoneline_reference") //cuando tenemos mas de un jsobackrefence  hay que ponerle value.
    private PhoneLine phoneLine;



}
