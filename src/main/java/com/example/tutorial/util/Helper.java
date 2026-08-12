package com.example.tutorial.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Helper {

    private Helper() {
        // Prevent creating Helper objects
    }

    /**
     * Merge common values into each item in a list.
     */
    public static List<Map<String, Object>> mergeIntoEach(
            List<Map<String, Object>> items,
            Map<String, Object> common
    ) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> item : items) {

            Map<String, Object> merged = new HashMap<>(item);

            merged.putAll(common);

            result.add(merged);
        }

        return result;
    }
}