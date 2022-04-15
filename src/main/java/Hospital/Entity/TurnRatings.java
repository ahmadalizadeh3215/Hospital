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
    private String visitDate;
    @OneToOne
    private Patient patient;
    @ManyToOne
    private Clinic clinic;
    @OneToOne
    private Prescription prescription;


    public TurnRatings(String visitDate, Patient patient, Clinic clinic,Prescription prescription) {
        this.visitDate = visitDate;
        this.patient = patient;
        this.clinic = clinic;
        this.prescription=prescription;

    }

    @Override
    public String toString() {
        return "TurnRatings{" +
                " visitDate=" + visitDate +
                ", patient=" + patient.getFirstName() +" "+patient.getLastName()
                +", nationalcode ="+patient.getNationalCode()+
                ", clinic=" + clinic.getName() +
                 ", expertise= "+clinic.getPhysician().getExpertise() +
                '}'+"\n";
    }
}
