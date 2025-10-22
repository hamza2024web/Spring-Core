package com.spring.entity;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2,max = 50,message = "Le nom doit contenir entre 2 et 50 caratères")
    private String name;

    @NotBlank(message = "l'email est obligatoire")
    @Email(message = "L'email doit étre valide")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "le mot de passe obligatoire")
    @Size(min = 6 , max = 100 , message = "le mot de pass doit contenir au moins 6 caratéres")
    private String password;

    public User() {}

    public User (String name , String email , String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
