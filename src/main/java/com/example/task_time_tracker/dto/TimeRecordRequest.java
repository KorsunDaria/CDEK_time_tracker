package com.example.task_time_tracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeRecordRequest {
    @NotNull(message = "ID сотрудника обязателен")
    private Long employeeId;

    @NotNull(message = "ID задачи обязателен")
    private Long taskId;

    @NotNull(message = "Время начала обязательно")
    private LocalDateTime startTime;

    @NotNull(message = "Время окончания обязательно")
    private LocalDateTime endTime;

    private String description;
}