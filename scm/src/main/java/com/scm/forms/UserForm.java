package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "Username is required")
    @Size(min = 3,max = 50,message = "Username should contain atleast 3 characters.")
    private String name;
    @Email(message = "Invalid Email address")
    @NotBlank(message = "Email is required")
    private String email;
    @Size(min = 4,message = "Password length must be 4 characters long")
    private String password;
    @Size(min = 8,max = 12,message = "Invalid Phone Number")
    private String contact;
    private String about;

}
