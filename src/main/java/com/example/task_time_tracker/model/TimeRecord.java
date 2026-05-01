package com.example.task_time_tracker.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeRecord {
    //ID, ID сотрудника, ID задачи, Время начала, Время окончания, Описание работ
    private Long id;
    private Long employeeId;
    private Long taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
