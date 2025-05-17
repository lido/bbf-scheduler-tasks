package com.lido.bffschedulertasks.infrastructure.client;

import com.lido.bffschedulertasks.business.dto.in.PhoneDTORequest;
import com.lido.bffschedulertasks.business.dto.out.PhoneDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "phone", url = "${phone.url}")
public interface PhoneClient {

    @PutMapping("/phone")
    PhoneDTOResponse updatePhone(@RequestHeader("Authorization") String token,
                                 @RequestParam("id") Long idPhone,
                                 @RequestBody PhoneDTORequest phoneDTO);

    @PostMapping("/phone")
    PhoneDTOResponse resgisterPhone(@RequestHeader("Authorization") String token,
                                    @RequestBody PhoneDTORequest phoneDTO);

}
