package Hospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String name;
    @ManyToOne
    private Physician physician;

    public Clinic(String name, Physician physician) {
        this.name = name;
        this.physician = physician;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "name='" + name + '\'' +
                ", physician=" + physician.getFirstName() +" "+physician.getLastName()+"  ,expertise"+physician.getExpertise()+ '\'' +
                '}';
    }
}
