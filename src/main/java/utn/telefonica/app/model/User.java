package utn.telefonica.app.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private City city;

    @OneToMany(mappedBy = "user")
    private List<PhoneLine> phoneLines;

    @OneToMany(mappedBy = "user")
    private List<Bill> bills;

    @Column(name = "user_type")
    public UserType userType;

    @Column(unique = true, nullable = false)
    public String dni;
}
