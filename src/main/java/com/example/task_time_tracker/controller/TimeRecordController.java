package com.example.task_time_tracker.controller;

import com.example.task_time_tracker.dto.TimeRecordRequest;
import com.example.task_time_tracker.model.TimeRecord;
import com.example.task_time_tracker.service.TimeRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/time-records")
@RequiredArgsConstructor
public class TimeRecordController {

    private final TimeRecordService timeRecordService;

    @PostMapping
    public TimeRecord saveRecord(@Valid @RequestBody TimeRecordRequest request) {
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setEmployeeId(request.getEmployeeId());
        timeRecord.setTaskId(request.getTaskId());
        timeRecord.setStartTime(request.getStartTime());
        timeRecord.setEndTime(request.getEndTime());
        timeRecord.setDescription(request.getDescription());

        return timeRecordService.saveTimeRecord(timeRecord);
    }

    @GetMapping("/employee/{employeeId}")
    public List<TimeRecord> getRecords(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return timeRecordService.getByEmployeeAndPeriod(employeeId, startTime, endTime);
    }

    @GetMapping("/employee/{employeeId}/total")
    public long getTotalTime(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return timeRecordService.calculateTotalMinutesSpent(employeeId, startTime, endTime);
    }
}