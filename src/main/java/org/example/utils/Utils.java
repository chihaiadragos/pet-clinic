package org.example.utils;

import org.example.exception.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class Utils {
    public static Integer generateBadgeID() {
        return new Random().nextInt(1, 10000);
    }

    public static String generateCollarId() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < 4; index++) {
            char randomChar = (char) random.nextInt(65, 91);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static LocalDateTime convertToDateTime(String appointmentDate, String appointmentTime) throws InvalidDateTimeException {
        try {
            LocalDate localDate = LocalDate.parse(appointmentDate);
            LocalTime localTime = LocalTime.parse(appointmentTime);
            return LocalDateTime.of(localDate, localTime);
        } catch (Exception e) {
            throw new InvalidDateTimeException();
        }
    }
}
