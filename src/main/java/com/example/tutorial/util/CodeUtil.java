package com.example.tutorial.util;

import java.security.SecureRandom;
import java.util.UUID;

public class CodeUtil {

    private static final SecureRandom RANDOM = new SecureRandom();

    private CodeUtil() {
        // Prevent creating an object from this utility class
    }

    // 12 -> 00012
    public static String numLenFormat(long number, int length) {

        if (length <= 0) {
            length = 5;
        }

        return String.format("%0" + length + "d", number);
    }

    // Example: ORD-A3F82B91-123456
    public static String generateRandomCode(String prefix) {

        String randomString = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();

        int randomNumber = 100000 + RANDOM.nextInt(900000);

        return prefix + "-" + randomString + "-" + randomNumber;
    }

    // Example: ORD-A93BC824F02E...
    public static String generateUniqueCode(String prefix) {

        String random = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .toUpperCase();

        return prefix + "-" + random;
    }

    // Example: id = 12 → ORD0012
    public static String generateRunningCode(
            String prefix,
            int length,
            long id
    ) {
        return prefix + numLenFormat(id, length);
    }
}