package com.weather.demo.util;

import java.util.UUID;

public class Utils {

    /**
     * Generates a new UUID
     * @return A String representation of a new UUID
     */
    public static String getNewGuid() {
        return UUID.randomUUID().toString().toUpperCase();
    }

}
