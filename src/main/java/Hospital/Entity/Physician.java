package Hospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Physician extends Person{
    @Column(length = 50)
    private String Expertise;
    public Physician(Integer nationalCode, String firstName, String lastName, String phoneNumber, String password, String expertise) {
        super(nationalCode, firstName, lastName, phoneNumber, password);
        Expertise = expertise;
    }
    @Override
    public String toString() {
        return "Physician{" +
                "nationalCode=" + getNationalCode() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getPhoneNumber() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", expertise='" + getExpertise() + '\'' +
                '}';
    }
}
