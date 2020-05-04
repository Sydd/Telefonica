package utn.telefonica.app.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Costumer {

    private int id_costumer; //Entity
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Date created_at;
    private int id_city;




}
