package utn.telefonica.app.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Costumer {
    @Id
    @GeneratedValue
    private int id_costumer; //Entity
    private String firstname;
    private String lastname;
    private String username;
    private String password;
   // private Date created_at;
    private int id_city;




}
