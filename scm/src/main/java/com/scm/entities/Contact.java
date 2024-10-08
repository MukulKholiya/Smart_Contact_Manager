package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "contact")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    @Id
    private String Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String contact;
    private String address;

    private String picture;
    @Builder.Default
    private String linnkedIn=null;
    @Builder.Default
    private boolean favourite = false;
    @ManyToOne
    private User user;
}
