package com.challenge.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class User {

    @Id
    private int Id;

    private String fullName;

    private String email;

    private String nickname;

    private String password;

    private LocalDate createAt;
}
