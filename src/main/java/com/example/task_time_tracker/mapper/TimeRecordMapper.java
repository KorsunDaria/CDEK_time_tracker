package com.example.task_time_tracker.mapper;

import com.example.task_time_tracker.model.TimeRecord;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TimeRecordMapper {

    @Insert("INSERT INTO time_records (employee_id, task_id, start_time, end_time, description) " +
            "VALUES (#{employeeId}, #{taskId}, #{startTime}, #{endTime}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(TimeRecord timeRecord);

    @Select("SELECT id, employee_id, task_id, start_time, end_time, description " +
            "FROM time_records " +
            "WHERE employee_id = #{employeeId} " +
            "  AND start_time <= #{endTime} " +
            "  AND end_time >= #{startTime}")
    List<TimeRecord> findByEmployeeAndPeriod(
            @Param("employeeId") Long employeeId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}