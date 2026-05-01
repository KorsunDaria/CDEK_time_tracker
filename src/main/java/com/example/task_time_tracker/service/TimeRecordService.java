package com.example.task_time_tracker.service;

import com.example.task_time_tracker.mapper.TimeRecordMapper;
import com.example.task_time_tracker.model.TimeRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeRecordService {
    private final TimeRecordMapper timeRecordMapper;
    private final TaskService taskService;

    public TimeRecord saveTimeRecord(TimeRecord timeRecord) {
        taskService.getTaskById(timeRecord.getTaskId());

        if (timeRecord.getStartTime().isAfter(timeRecord.getEndTime())) {
            throw new IllegalArgumentException("Время начала не может быть позже времени окончания");
        }

        timeRecordMapper.save(timeRecord);
        return timeRecord;
    }

    public List<TimeRecord> getByEmployeeAndPeriod(Long employeeId,
                                                   LocalDateTime startTime,
                                                   LocalDateTime endTime) {
        return timeRecordMapper.findByEmployeeAndPeriod(employeeId, startTime, endTime);
    }

    public long calculateTotalMinutesSpent(Long employeeId, LocalDateTime startTime, LocalDateTime endTime) {
        List<TimeRecord> records = timeRecordMapper.findByEmployeeAndPeriod(employeeId, startTime, endTime);

        return records.stream()
                .mapToLong(record -> {
                    LocalDateTime actualStart = record.getStartTime().isBefore(startTime)
                            ? startTime
                            : record.getStartTime();

                    LocalDateTime actualEnd = record.getEndTime().isAfter(endTime)
                            ? endTime
                            : record.getEndTime();

                    return Duration.between(actualStart, actualEnd).toMinutes();
                })
                .sum();
    }
}