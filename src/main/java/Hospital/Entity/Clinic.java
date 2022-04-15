package Hospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

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
    private Integer capacity;
    public Clinic(String name, Physician physician,Integer capacity) {
        this.name = name;
        this.physician = physician;
        this.capacity=capacity;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                " id= "+id+
                " name Clinic='" + name + '\'' +
                ", physician name= " + physician.getFirstName() +" "+physician.getLastName()+
                "  ,expertise= "+physician.getExpertise()+ '\'' +
                " ,capacity= "+capacity+
                '}'+"\n";
    }
}
