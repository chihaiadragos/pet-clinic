package org.example.utils;

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

}
