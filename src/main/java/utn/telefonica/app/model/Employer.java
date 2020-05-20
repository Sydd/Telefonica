package utn.telefonica.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_employer")
        private int id; // Entity
        @Column(name = "first_name")
        private String firstName;
        @Column(name = "last_name")
        private String lastName;

        private String username;
        
        private String password;
}
