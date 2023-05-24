package ru.voronovskii.util.spring;

import org.springframework.boot.SpringApplication;

import java.util.HashMap;
import java.util.Map;

public final class DefaultProfileUtil {

    private DefaultProfileUtil() {
    }

    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();
        defProperties.put("spring.profiles.default", "dev");
        app.setDefaultProperties(defProperties);
    }
}
