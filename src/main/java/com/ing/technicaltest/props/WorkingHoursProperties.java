package com.ing.technicaltest.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalTime;
import java.util.TimeZone;

@Data
@ConfigurationProperties(prefix = "working-hours")
public class WorkingHoursProperties {
    private String timezone;

    private int start;

    private int end;
}
