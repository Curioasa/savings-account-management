package com.ing.technicaltest.services;

import com.ing.technicaltest.exceptions.OutsideOfWorkingHoursException;
import com.ing.technicaltest.props.WorkingHoursProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class WorkingHoursService {
    private LocalTime startOfWorkingHours;
    private LocalTime endOfWorkingHours;
    private Clock clock;

    @Autowired
    public WorkingHoursService(WorkingHoursProperties workingHoursProperties, Clock clock) {
        ZoneId zoneId = ZoneId.of(workingHoursProperties.getTimezone());
        startOfWorkingHours = LocalTime.of(workingHoursProperties.getStart(), 0);
        endOfWorkingHours = LocalTime.of(workingHoursProperties.getEnd(), 0);
        clock.withZone(zoneId);
        this.clock = clock;
    }

    public void checkWorkingHours() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(clock);
        LocalTime now = zonedDateTime.toLocalTime();

        if (zonedDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                zonedDateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new OutsideOfWorkingHoursException("Request to add savings account occurred during the weekend");
        }
        if (now.isBefore(startOfWorkingHours) || now.isAfter(endOfWorkingHours)) {
            throw new OutsideOfWorkingHoursException("Request to add savings account occurred at: " + now.toString());
        }
    }
}
