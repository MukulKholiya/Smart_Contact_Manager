package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;
import com.scm.entities.Contact;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


@Entity(name="User")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    private String userId;

    @Column(nullable = false)
    private String userName;

    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    private String about;
    private String profilePic;
    private String phoneNumber;
//    Information
    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
//    self,google,gitHub
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        list of roles[USER,ADMIN]
//        Collection of SimpleGrantedAuthority[roles{ADMIN,USER}]
        return roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    public String getName(){
        return this.userName;
    }

    // For this project we are going to use email ID as username
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
