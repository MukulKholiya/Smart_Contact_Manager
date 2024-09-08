package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Invalid Email address")
    private String email;
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "Invalid Phone Number")
    private String phoneNumber;
    private String address;
    private boolean favourite;
    private MultipartFile profileImage;
}
