package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "total_cost")
    private int totalCost;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "due_date")
    private Date dueDate;

     @Column(name = "is_payed")
    private Boolean isPayed;

    @ManyToOne(fetch = FetchType.EAGER)
    private PhoneLine phoneLine;

    @OneToMany(mappedBy = "bill")
    @JsonIgnore
    private List<Call> calls;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
