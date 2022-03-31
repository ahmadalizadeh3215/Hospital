package Hospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Patient extends Person {
    @Column(length = 100)
    private String PatientDossier;

    public Patient(Integer nationalCode, String firstName, String lastName, String phoneNumber
            , String password, String patientDossier) {
        super(nationalCode, firstName, lastName, phoneNumber, password);
        PatientDossier = patientDossier;

    }
    @Override
    public String toString() {
        return "Patient{" +
                "nationalCode=" + getNationalCode() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getPhoneNumber() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", PatientDossier='" + getPatientDossier() + '\'' +
                '}';
    }
}
