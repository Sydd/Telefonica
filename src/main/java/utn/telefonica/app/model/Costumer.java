package utn.telefonica.app.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name ="costumers")
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_costumer")
    private int id; //Entity
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String password;
    @Column(name = "created_at", columnDefinition = "date default now()")
    private java.sql.Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private City city;


    @OneToMany(mappedBy = "customer")
    private List<Line> lines;




}
