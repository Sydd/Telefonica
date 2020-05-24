package utn.telefonica.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="phonelines")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_line")
    private int id; //Entity
    private String lineType;
    @Column(name = "line_number")
    private String lineNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Customer customer;


    @OneToMany(mappedBy = "phonelines")
    private List<Bill> bills;

    @OneToMany(mappedBy = "phonelines")
    private List<Call> calls;
}
