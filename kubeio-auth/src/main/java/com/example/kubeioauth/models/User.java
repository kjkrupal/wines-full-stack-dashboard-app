package com.example.kubeioauth.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Data
@Table(name = "USER", schema = "public")
public class User {
    @Id
    @Column
    private String userName;
    @Column
    private String password;
}