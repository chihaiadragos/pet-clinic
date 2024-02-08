package org.example.validator;

import org.example.entities.Appointment;
import org.example.entities.Veterinary;

import java.time.LocalDateTime;

public class AppointmentValidator {
    public boolean isNewAppointmentInvalid(LocalDateTime newDateTime, Veterinary veterinary) {
        long numberOfConflictingAppointments = veterinary.getAppointments()
                .stream()
                .map(Appointment::getDateTime)
                .filter(existingDateTime -> existingDateTime.isAfter(newDateTime.minusMinutes(30)))
                .filter(existingDateTime -> existingDateTime.isBefore(newDateTime.plusMinutes(30)))
                .count();
        System.out.println("Number of conflicting appointments: " + numberOfConflictingAppointments);
        return numberOfConflictingAppointments > 0;
    }
}