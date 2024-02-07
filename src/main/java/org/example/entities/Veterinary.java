package org.example.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Veterinary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer veterinaryID;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private Integer badgeID;
    @OneToMany(mappedBy = "veterinary")
    private List<Appointment> appointments = new ArrayList<>();

    public Integer getVeterinaryID() {
        return veterinaryID;
    }

    public void setVeterinaryID(Integer veterinaryID) {
        this.veterinaryID = veterinaryID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(Integer badgeID) {
        this.badgeID = badgeID;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Veterinary {");
        sb.append("First Name: '").append(firstName).append('\'');
        sb.append(", Last Name: '").append(lastName).append('\'');
        sb.append(", badgeID: ").append(badgeID);
        sb.append('}');
        return sb.toString();
    }
}