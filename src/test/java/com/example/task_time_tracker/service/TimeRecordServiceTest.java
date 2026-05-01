package com.example.task_time_tracker.service;

import com.example.task_time_tracker.mapper.TimeRecordMapper;
import com.example.task_time_tracker.model.TimeRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeRecordServiceTest {

    @Mock
    private TimeRecordMapper timeRecordMapper;

    @InjectMocks
    private TimeRecordService timeRecordService;

    @Test
    void calculateTotalMinutesSpent_ShouldCalculateCorrectly_WithOverlappingPeriods() {
        Long employeeId = 1L;
        LocalDateTime searchStart = LocalDateTime.of(2026, 5, 1, 10, 0);
        LocalDateTime searchEnd = LocalDateTime.of(2026, 5, 1, 18, 0);


        TimeRecord record1 = new TimeRecord(1L, employeeId, 1L,
                LocalDateTime.of(2026, 5, 1, 9, 0),
                LocalDateTime.of(2026, 5, 1, 11, 0), "Часть вне периода");

        TimeRecord record2 = new TimeRecord(2L, employeeId, 1L,
                LocalDateTime.of(2026, 5, 1, 12, 0),
                LocalDateTime.of(2026, 5, 1, 13, 0), "Полностью внутри");

        TimeRecord record3 = new TimeRecord(3L, employeeId, 1L,
                LocalDateTime.of(2026, 5, 1, 17, 30),
                LocalDateTime.of(2026, 5, 1, 19, 0), "Конец вне периода");

        when(timeRecordMapper.findByEmployeeAndPeriod(employeeId, searchStart, searchEnd))
                .thenReturn(List.of(record1, record2, record3));


        long totalMinutes = timeRecordService.calculateTotalMinutesSpent(employeeId, searchStart, searchEnd);

        assertEquals(150, totalMinutes);
    }
}