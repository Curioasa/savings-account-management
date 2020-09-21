package com.ing.technicaltest.services;

import com.ing.technicaltest.exceptions.OutsideOfWorkingHoursException;
import com.ing.technicaltest.props.WorkingHoursProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WorkingHoursServiceTest {
    private WorkingHoursService victim;

    @Mock
    private WorkingHoursProperties props;

    @Test
    public void checkWorkingHours() {
        when(props.getTimezone()).thenReturn("GMT");
        when(props.getStart()).thenReturn(8);
        when(props.getEnd()).thenReturn(18);
        Clock clock = Clock.fixed(
                Instant.parse("2020-09-21T12:34:56Z"), ZoneOffset.UTC
        );
        victim = new WorkingHoursService(props, clock);

        victim.checkWorkingHours();

        verify(props).getTimezone();
        verify(props).getStart();
        verify(props).getEnd();
        verifyNoMoreInteractions(props);
    }

    @Test(expected = OutsideOfWorkingHoursException.class)
    public void checkWorkingHoursBeforeStart() {
        when(props.getTimezone()).thenReturn("GMT");
        when(props.getStart()).thenReturn(8);
        when(props.getEnd()).thenReturn(18);
        Clock clock = Clock.fixed(
                Instant.parse("2020-09-21T06:34:56Z"), ZoneOffset.UTC
        );
        victim = new WorkingHoursService(props, clock);

        victim.checkWorkingHours();

        verify(props).getTimezone();
        verify(props).getStart();
        verify(props).getEnd();
        verifyNoMoreInteractions(props);
    }

    @Test(expected = OutsideOfWorkingHoursException.class)
    public void checkWorkingHoursAfterEnd() {
        when(props.getTimezone()).thenReturn("GMT");
        when(props.getStart()).thenReturn(8);
        when(props.getEnd()).thenReturn(18);
        Clock clock = Clock.fixed(
                Instant.parse("2020-09-21T21:34:56Z"), ZoneOffset.UTC
        );
        victim = new WorkingHoursService(props, clock);

        victim.checkWorkingHours();

        verify(props).getTimezone();
        verify(props).getStart();
        verify(props).getEnd();
        verifyNoMoreInteractions(props);
    }
}