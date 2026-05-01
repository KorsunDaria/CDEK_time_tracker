package com.example.task_time_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequest {
    @NotBlank(message = "Название задачи не может быть пустым")
    private String title;
    private String description;
}