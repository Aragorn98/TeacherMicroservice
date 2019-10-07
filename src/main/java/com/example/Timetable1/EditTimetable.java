//tag::all[]
//tag::allButValidation[]
package com.example.Timetable1;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Getter
//@Setter
@Data
@Entity
@Table(name = "teacher")
public class EditTimetable implements Serializable {


//    public EditTimetable(@NotNull @Size(min = 3, message = "Academic title must be at least 3 characters long") AcademicTitle academicTitle) {
//        this.title = academicTitle;
//    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    //end::allButValidation[]
//    @NotBlank(message="Name is required")
//    // tag::allButValidation[]
//    private String name;
    //end::allButValidation[]

//    @NotNull
//    @Size(min=3, message="Academic title must be at least 3 characters long")
//    @NotBlank(message="Academic title is required")
//    // tag::allButValidation[]
//    private AcademicTitle title;
//    //end::allButValidation[]
//    public static enum AcademicTitle {
//        SENIOR_LECTURER, PHD, TUTOR, MASTER
//    }

//    @NotNull
//    @Size(min=3, message="Academic title must be at least 3 characters long")

    // tag::allButValidation[]
    @NotEmpty(message="Academic title is required")
    private String title;
    //end::allButValidation[]


//    @NotNull
//    @Size(min=5,max=20, message="Your login must be at least 5 characters and maximum 20 characters long")
    // tag::allButValidation[]
@NotEmpty(message="Login is required")
private String username;
    //end::allButValidation[]

//    @NotNull
//    @Size(min=5,max=20, message="Your password must be at least 5 characters and maximum 20 characters long")
    // tag::allButValidation[]
@NotEmpty(message="Password is required")
private String password;
    //end::allButValidation[]


    @NotEmpty(message="Name is required")
    private String name;


    //    @NotNull
    //    @Size(min=5,max=20, message="Your password must be at least 5 characters and maximum 20 characters long")
    // tag::allButValidation[]
    @NotEmpty(message="Role is required")
    private String role;
    //end::allButValidation[]


    private String ROLE_PREFIX = "ROLE_";

    public Teacher toTeacher(PasswordEncoder passwordEncoder) {
        return new Teacher(
                username, passwordEncoder.encode(password),
                name, title, role);
    }

    //private String department;
}
//end::allButValidation[]
//end::all[]
