package com.lido.bffschedulertasks.infrastructure.client;

import com.lido.bffschedulertasks.business.dto.in.TasksDTORequest;
import com.lido.bffschedulertasks.business.dto.out.TasksDTOResponse;
import com.lido.bffschedulertasks.business.enums.StatusNotification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "scheduler-tasks", url = "${scheduler-tasks.url}")
public interface TasksClient {

    @PostMapping
    TasksDTOResponse registerTasks(@RequestHeader("Authorization") String token,
                                   @RequestBody TasksDTORequest tasksDTO);

    @GetMapping("/events")
    List<TasksDTOResponse> searchScheduledTasksByPeriod(@RequestHeader("Authorization") String token,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate);

    @GetMapping
    List<TasksDTOResponse> findByUserEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTasksById(@RequestHeader("Authorization") String token, @RequestParam String id);

    @PatchMapping
    TasksDTOResponse updateStatusNotification(@RequestHeader("Authorization") String token,
                                              @RequestParam("status") StatusNotification status,
                                              @RequestParam("id") String id);

    @PutMapping
    TasksDTOResponse updateTasks(@RequestHeader("Authorization") String token,
                                 @RequestBody TasksDTOResponse tasksDTO,
                                 @RequestParam("id") String id);
}
