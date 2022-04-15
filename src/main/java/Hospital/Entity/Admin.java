package Hospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor

@Getter
@Setter
@Entity
public class Admin extends Person{
    public Admin(Integer nationalCode, String firstName, String lastName, String phoneNumber, String password) {
        super(nationalCode, firstName, lastName, phoneNumber, password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "nationalCode=" + getNationalCode() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getPhoneNumber() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
