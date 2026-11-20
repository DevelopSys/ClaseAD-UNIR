package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAPI {

    private String gender;
    private NameAPI name;
    private String email;
    private DobAPI dob;
    private String phone;
    private String cell;
    private String nat;
}
