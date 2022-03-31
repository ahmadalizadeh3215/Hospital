package Hospital.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Person {
    @Id
    private Integer nationalCode;
    @Column(length = 30)
    private String firstName;
    @Column (length = 50)
    private String lastName;
    @Column (length = 11)
    private String phoneNumber;
    @Column (columnDefinition = "char(10)")
    private String password;

}
