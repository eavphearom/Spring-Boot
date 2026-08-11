package com.example.tutorial.util;

public class PhoneUtil {

    private PhoneUtil() {
        // Prevent creating an object from this utility class
    }

    public static String formatPhoneNumber(String phone) {

        if (phone == null || phone.isBlank()) {
            return null;
        }

        // Remove spaces
        phone = phone.trim().replace(" ", "");

        // 012345678 → 85512345678
        if (phone.startsWith("0")) {
            return "855" + phone.substring(1);
        }

        // 85512345678 → keep same
        if (phone.startsWith("855")) {
            return phone;
        }

        // +85512345678 → 85512345678
        if (phone.startsWith("+855")) {
            return phone.substring(1);
        }

        return null;
    }
}