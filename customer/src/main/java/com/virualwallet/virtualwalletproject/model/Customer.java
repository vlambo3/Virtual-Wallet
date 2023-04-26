package com.virualwallet.virtualwalletproject.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "customers")
@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "The email can´t be null")
    private String email;

    private String phone;

    @NotBlank(message = "The name can´t be null")
    private String name;

    @NotBlank(message = "The lastname can´t be null")
    private String lastname;

    @NotBlank(message = "The dni can´t be null")
    private String dni;

    private String alias;

    private String cvu;

    private boolean deleted = Boolean.FALSE;

    public Customer() {
    }

    public Customer(Long id, String email, String phone, String name, String lastname, String dni, String alias, String cvu, boolean deleted) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.alias = alias;
        this.cvu = cvu;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
