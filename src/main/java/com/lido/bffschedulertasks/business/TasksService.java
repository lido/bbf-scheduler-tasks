package com.lido.bffschedulertasks.business;


import com.lido.bffschedulertasks.business.dto.out.TasksDTOResponse;
import com.lido.bffschedulertasks.business.enums.StatusNotification;
import com.lido.bffschedulertasks.infrastructure.client.TasksClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksClient tasksClient;


    public TasksDTOResponse registerTask(String token, TasksDTOResponse tasksDTO) {
        return tasksClient.registerTasks(token, tasksDTO);
    }

    public List<TasksDTOResponse> searchScheduledTasksByPeriod(String token, LocalDateTime startDate, LocalDateTime finishDate) {
        return tasksClient.searchScheduledTasksByPeriod(token, startDate, finishDate);
    }

    public List<TasksDTOResponse> findByUserEmail(String token) {
        return tasksClient.findByUserEmail(token);
    }

    public void deleteTasksById(String token, String id) {
        tasksClient.deleteTasksById(token, id);
    }

    public TasksDTOResponse updateStatus(String token, StatusNotification statusNotification, String id) {
        return tasksClient.updateStatusNotification(token, statusNotification, id);

    }

    public TasksDTOResponse updateTasks(String token, TasksDTOResponse tasksDTO, String id) {
        return tasksClient.updateTasks(token, tasksDTO, id);
    }
}
