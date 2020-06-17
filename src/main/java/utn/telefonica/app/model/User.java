package utn.telefonica.app.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.telefonica.app.model.Bill;
import utn.telefonica.app.model.City;
import utn.telefonica.app.model.PhoneLine;
import utn.telefonica.app.model.enums.UserType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id; //Entity

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private City city;

    @OneToMany(mappedBy = "users")
    private List<PhoneLine> phoneLines;

    @Column(name = "user_type")
    public UserType userType;


}