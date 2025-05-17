package com.lido.bffschedulertasks.business;

import com.lido.bffschedulertasks.business.dto.in.AddressDTORequest;
import com.lido.bffschedulertasks.business.dto.out.AddressDTOResponse;
import com.lido.bffschedulertasks.infrastructure.client.AddressClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressClient addressClient;

    public AddressDTOResponse updateAddress(String token, Long idAddress, AddressDTORequest addressDTO){
        return addressClient.updateAddress(token, idAddress, addressDTO);
    }

    public AddressDTOResponse registerAddress(String token, AddressDTORequest addressDTO){
        return addressClient.registerAddress(token, addressDTO);
    }

}
