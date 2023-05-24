package ru.voronovskii.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app", ignoreInvalidFields = true)
public class ApplicationProperties {
    @Value("${app.props.user_pass}")
    private String userPass;

    @Value("${app.props.admin_pass}")
    private String adminPass;

}
