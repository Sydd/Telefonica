package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Column(name = "state",columnDefinition = "boolean default false")
    private boolean state;

    @OneToMany(mappedBy = "phoneLine")
    @JsonIgnore
    private List<Bill> bills;

    @OneToMany(mappedBy = "phoneLine")
    @JsonIgnore
    private List<Call> callList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;


}
