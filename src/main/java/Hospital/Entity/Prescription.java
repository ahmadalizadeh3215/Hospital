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
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date visitDate;
    @Column(length = 150)
    private String Description;
    @OneToOne
    private Patient patient;
    @OneToOne
    private Physician physician;

    public Prescription(Date visitDate, String description, Patient patient, Physician physician) {
        this.visitDate = visitDate;
        Description = description;
        this.patient = patient;
        this.physician = physician;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "visitDate=" + visitDate + '\'' +
                ", patient=" + patient.getFirstName() +" "+patient.getLastName()+ "  , nationalCode="+patient.getNationalCode()+ '\'' +
                ", physician=" + physician.getFirstName() +" "+physician.getLastName()+"  ,expertise"+physician.getExpertise()+ '\'' +
                ", Description='" + Description +
                '}';
    }
}
