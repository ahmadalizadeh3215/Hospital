package Hospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TurnRatings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date visitDate;
    @OneToOne
    private Patient patient;
    @ManyToOne
    private Clinic clinic;

    public TurnRatings(Date visitDate, Patient patient, Clinic clinic) {
        this.visitDate = visitDate;
        this.patient = patient;
        this.clinic = clinic;
    }

    @Override
    public String toString() {
        return "TurnRatings{" +
                "visitDate=" + visitDate +
                ", patient=" + patient.getFirstName() +" "+patient.getLastName()
                +" , nationalcode ="+patient.getNationalCode()+
                ", clinic=" + clinic.getName() +" , "+
                 clinic.getPhysician().getExpertise() +
                '}';
    }
}
