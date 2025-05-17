package com.lido.bffschedulertasks.infrastructure.client;

import com.lido.bffschedulertasks.business.dto.out.TasksDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notification", url = "${notification.url}")
public interface EmailClient {

    void sendEmail(@RequestBody TasksDTOResponse dto);

}
