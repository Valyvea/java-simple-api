package com.train2.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name")
    private String name;
    @Column(name = "NIK", length=16, unique = true)
    private String nik;
    @Column(name = "NPWP", length=15, unique = true)
    private String npwp;
    public Customer() {
    }
    public Customer(long id, String name, String nik, String npwp) {
        this.id = id;
        this.name = name;
        this.nik = nik;
        this.npwp = npwp;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNIK() {
        return nik;
    }
    public void setNIK(String nik) {
        this.nik = nik;
    }
    public String getNPWP() {
        return npwp;
    }
    public void setNPWP(String npwp) {
        this.npwp = npwp;
    }
}
