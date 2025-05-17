package com.lido.bffschedulertasks.business;

import com.lido.bffschedulertasks.business.dto.in.PhoneDTORequest;
import com.lido.bffschedulertasks.business.dto.out.PhoneDTOResponse;
import com.lido.bffschedulertasks.infrastructure.client.PhoneClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneClient phoneClient;

    public PhoneDTOResponse updatePhone(String token, Long idPhone, PhoneDTORequest phoneDTO){
        return phoneClient.updatePhone(token, idPhone, phoneDTO);
    }

    public PhoneDTOResponse registerPhone(String token, PhoneDTORequest phoneDTO){
        return phoneClient.resgisterPhone(token, phoneDTO);
    }
}
