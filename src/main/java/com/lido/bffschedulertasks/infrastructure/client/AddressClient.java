package com.lido.bffschedulertasks.infrastructure.client;

import com.lido.bffschedulertasks.business.dto.in.AddressDTORequest;
import com.lido.bffschedulertasks.business.dto.out.AddressDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "address", url = "${address.url}")
public interface AddressClient {

   @PutMapping("/address")
   AddressDTOResponse updateAddress(@RequestHeader("Authorization") String token,
                                    @RequestParam("id") Long idAddress,
                                    @RequestBody AddressDTORequest addressDTO);

    @PostMapping("/address")
    AddressDTOResponse registerAddress(@RequestHeader("Authorization") String token,
                                       @RequestBody AddressDTORequest addressDTO);

}
