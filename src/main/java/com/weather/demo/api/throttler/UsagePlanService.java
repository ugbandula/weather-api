package com.weather.demo.api.throttler;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UsagePlanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsagePlanService.class);

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, this::newBucket);
    }

    private Bucket newBucket(String apiKey) {
        UsagePlan usagePlan = UsagePlan.resolvePlanFromApiKey(apiKey);
        return Bucket4j.builder()
                .addLimit(usagePlan.getLimit())
                .build();
    }

}
