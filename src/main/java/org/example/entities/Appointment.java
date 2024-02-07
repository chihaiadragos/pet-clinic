package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private Integer appointmentId;
    @ManyToOne
    @JoinColumn
    private Veterinary veterinary;
    @ManyToOne
    @JoinColumn
    private Pet pet;
    private LocalDateTime dateTime;

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Veterinary getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(Veterinary veterinary) {
        this.veterinary = veterinary;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
