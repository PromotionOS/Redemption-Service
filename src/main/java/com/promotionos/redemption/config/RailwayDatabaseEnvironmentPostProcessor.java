package com.promotionos.redemption.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RailwayDatabaseEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> properties = new HashMap<>();

        String databaseUrl = firstPresent(
            environment.getProperty("JDBC_DATABASE_URL"),
            environment.getProperty("DB_URL"),
            environment.getProperty("DATABASE_PRIVATE_URL"),
            environment.getProperty("DATABASE_URL")
        );

        if (databaseUrl != null) {
            applyDatabaseUrl(databaseUrl, properties);
        } else {
            applySplitPostgresVariables(environment, properties);
        }

        if (!properties.isEmpty()) {
            environment.getPropertySources()
                .addFirst(new MapPropertySource("railwayDatabase", properties));
        }
    }

    private void applyDatabaseUrl(String databaseUrl, Map<String, Object> properties) {
        String jdbcUrl = toJdbcUrl(databaseUrl);
        properties.put("spring.datasource.url", jdbcUrl);
        properties.put("spring.flyway.url", jdbcUrl);

        URI uri = URI.create(databaseUrl.replaceFirst("^jdbc:", ""));
        String userInfo = uri.getUserInfo();
        if (userInfo != null) {
            String[] parts = userInfo.split(":", 2);
            properties.put("spring.datasource.username", decode(parts[0]));
            properties.put("spring.flyway.user", decode(parts[0]));
            if (parts.length > 1) {
                properties.put("spring.datasource.password", decode(parts[1]));
                properties.put("spring.flyway.password", decode(parts[1]));
            }
        }
    }

    private void applySplitPostgresVariables(ConfigurableEnvironment environment, Map<String, Object> properties) {
        String host = environment.getProperty("PGHOST");
        String port = environment.getProperty("PGPORT", "5432");
        String database = environment.getProperty("PGDATABASE");
        if (isBlank(host) || isBlank(database)) {
            return;
        }

        String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        properties.put("spring.datasource.url", jdbcUrl);
        properties.put("spring.flyway.url", jdbcUrl);

        putIfPresent(properties, "spring.datasource.username", environment.getProperty("PGUSER"));
        putIfPresent(properties, "spring.flyway.user", environment.getProperty("PGUSER"));
        putIfPresent(properties, "spring.datasource.password", environment.getProperty("PGPASSWORD"));
        putIfPresent(properties, "spring.flyway.password", environment.getProperty("PGPASSWORD"));
    }

    private String toJdbcUrl(String databaseUrl) {
        String uriValue = databaseUrl.startsWith("jdbc:")
            ? databaseUrl.substring("jdbc:".length())
            : databaseUrl;

        if (uriValue.startsWith("postgres://") || uriValue.startsWith("postgresql://")) {
            URI uri = URI.create(uriValue);
            String query = uri.getQuery() == null ? "" : "?" + uri.getQuery();
            int port = uri.getPort();
            String portPart = port == -1 ? "" : ":" + port;
            return "jdbc:postgresql://" + uri.getHost() + portPart + uri.getPath() + query;
        }

        if (databaseUrl.startsWith("jdbc:")) {
            return databaseUrl;
        }
        return databaseUrl;
    }

    private String firstPresent(String... values) {
        for (String value : values) {
            if (!isBlank(value)) {
                return value;
            }
        }
        return null;
    }

    private void putIfPresent(Map<String, Object> properties, String key, String value) {
        if (!isBlank(value)) {
            properties.put(key, value);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }
}
