package org.example.validator;

import org.example.entities.Appointment;
import org.example.entities.Veterinary;
import org.example.exception.InvalidAppointmentDateException;

import java.time.LocalDateTime;

public class AppointmentValidator {
    public void validateNewAppointmentDate(LocalDateTime newDateTime, Veterinary veterinary) throws InvalidAppointmentDateException {
        long numberOfConflictingAppointments = veterinary.getAppointments()
                .stream()
                .map(Appointment::getDateTime)
                .filter(existingDateTime -> existingDateTime.isAfter(newDateTime.minusMinutes(30)))
                .filter(existingDateTime -> existingDateTime.isBefore(newDateTime.plusMinutes(30)))
                .count();
        if (numberOfConflictingAppointments == 0) {
            throw new InvalidAppointmentDateException();
        }
    }
}