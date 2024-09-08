package com.scm.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private boolean favourite;
    private MultipartFile profileImage;
}
