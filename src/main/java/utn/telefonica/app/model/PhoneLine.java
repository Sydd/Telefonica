package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.telefonica.app.model.enums.PhonelineType;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="phonelines")
public class PhoneLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_line")
    private int id; //Entity

    @Column(name = "line_type")
    private PhonelineType lineType;

    @Column(name = "line_number",unique = true)
    private String lineNumber;

    @OneToMany(mappedBy = "phoneLine")
    private List<Bill> bills;

    @OneToMany(mappedBy = "phoneLine")
    private List<Call> callList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;
}
