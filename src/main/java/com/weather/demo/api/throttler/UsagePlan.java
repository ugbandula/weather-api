package com.weather.demo.api.throttler;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import java.time.Duration;

public enum UsagePlan {
    UNLIMITED_USER(1000),
    RESTRICTED_USER(5);

    private int bucketCapacity;

    private UsagePlan(int bucketCapacity) {
        this.bucketCapacity = bucketCapacity;
    }

    public Bandwidth getLimit() {
        return Bandwidth.classic(bucketCapacity, Refill.intervally(bucketCapacity, Duration.ofHours(1)));
    }

    public int bucketCapacity() {
        return bucketCapacity;
    }

    public static UsagePlan resolvePlanFromApiKey(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            return UNLIMITED_USER;
        } else if (apiKey.equals(RESTRICTED_USER)) {
            return RESTRICTED_USER;
        }
        return UNLIMITED_USER;
    }

}
