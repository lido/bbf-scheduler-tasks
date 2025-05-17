package com.lido.bffschedulertasks.controller;

import com.lido.bffschedulertasks.business.dto.in.TasksDTORequest;
import com.lido.bffschedulertasks.business.dto.out.TasksDTOResponse;
import com.lido.bffschedulertasks.infrastructure.client.TasksClient;
import com.lido.bffschedulertasks.business.enums.StatusNotification;
import com.lido.bffschedulertasks.infrastructure.exceptions.ResourceNotFoundException;
import com.lido.bffschedulertasks.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
@Tag(name = "Tasks", description = "Register Tasks User")
public class TasksController {

    private final TasksClient tasksClient;

    @PostMapping
    @Operation(summary = "Save Tasks", description = "Create a new tasks")
    @ApiResponse(responseCode = "200", description = "Tasks saved successfully")
    @ApiResponse(responseCode = "400", description = "Tasks already registered")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<TasksDTOResponse> registerTasks(@RequestHeader(name = "Authorization", required=false) String token,
                                                          @RequestBody TasksDTORequest tasksDTO) {
        return ResponseEntity.ok(tasksClient.registerTasks(token, tasksDTO));
    }

    @GetMapping("/events")
    @Operation(summary = "Search Tasks data by period",
            description = "Search Tasks data registered by period")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @ApiResponse(responseCode = "404", description = "Tasks not found")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<List<TasksDTOResponse>> searchScheduledTasksByPeriod(@RequestHeader(name = "Authorization", required=false) String token,
                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate) {

        return ResponseEntity.ok(tasksClient.searchScheduledTasksByPeriod(token, startDate, finishDate));
    }

    @GetMapping
    @Operation(summary = "Search Tasks data by email",
            description = "Search Tasks data")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @ApiResponse(responseCode = "404", description = "Tasks not found")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<List<TasksDTOResponse>> findByUserEmail(@RequestHeader(name = "Authorization", required=false) String token) {
        return ResponseEntity.ok(tasksClient.findByUserEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Delete Tasks by id",
            description = "Delete Tasks by id")
    @ApiResponse(responseCode = "Tasks", description = "Tasks deleted successfully")
    @ApiResponse(responseCode = "404", description = "Tasks not found")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<Void> deleteTasksById(@RequestHeader(name = "Authorization", required=false) String token, @RequestParam String id) {

        try {
            tasksClient.deleteTasksById(token, id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error deleting task by is, does not exist : ", e.getCause());
        }

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Update Tasks status",
            description = "Update Tasks status")
    @ApiResponse(responseCode = "200", description = "Tasks status updated")
    @ApiResponse(responseCode = "404", description = "Tasks not regitered")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<TasksDTOResponse> updateStatusNotification(@RequestHeader(name = "Authorization", required=false) String token,
                                                                     @RequestParam("status") StatusNotification status,
                                                                     @RequestParam("id") String id) {
        return ResponseEntity.ok(tasksClient.updateStatusNotification(token, status, id));
    }

    @PutMapping
    @Operation(summary = "Update Tasks",
            description = "Update Tasks")
    @ApiResponse(responseCode = "200", description = "Tasks updated")
    @ApiResponse(responseCode = "404", description = "Tasks not regitered")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<TasksDTOResponse> updateTasks(@RequestHeader(name = "Authorization", required=false) String token, @RequestBody TasksDTOResponse tasksDTO,
                                                        @RequestParam("id") String id) {
        return ResponseEntity.ok(tasksClient.updateTasks(token, tasksDTO, id));
    }
}
