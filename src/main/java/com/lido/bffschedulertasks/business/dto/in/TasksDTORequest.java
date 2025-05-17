package com.lido.bffschedulertasks.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lido.bffschedulertasks.business.enums.StatusNotification;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TasksDTORequest {

    private String nameTask;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfEvent;

}
