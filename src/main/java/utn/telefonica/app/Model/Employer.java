package utn.telefonica.app.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employer {

        private int id_employer; // Entity
        private String firstname;
        private String lastname;
        private String username;
        private String password;
}
