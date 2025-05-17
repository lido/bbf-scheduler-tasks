package com.lido.bffschedulertasks.business;

import com.lido.bffschedulertasks.business.dto.out.TasksDTOResponse;
import com.lido.bffschedulertasks.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void sendEmail(TasksDTOResponse dto){
        emailClient.sendEmail(dto);
    }
}
