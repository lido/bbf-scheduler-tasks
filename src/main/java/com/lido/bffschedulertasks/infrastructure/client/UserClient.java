package com.lido.bffschedulertasks.infrastructure.client;

import com.lido.bffschedulertasks.business.dto.in.LoginDTORequest;
import com.lido.bffschedulertasks.business.dto.in.UserDTORequest;
import com.lido.bffschedulertasks.business.dto.out.AddressDTOResponse;
import com.lido.bffschedulertasks.business.dto.out.PhoneDTOResponse;
import com.lido.bffschedulertasks.business.dto.out.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTOResponse findUserByEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTOResponse saveUser(@RequestBody UserDTORequest userDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest userDTO);

    @DeleteMapping("/{email}")
    void deleteUserByEmail(@RequestHeader("Authorization") String token,
                                                  @PathVariable("email") String email);

    @PutMapping
    UserDTOResponse updateUserData(@RequestHeader("Authorization") String token,
                                   @RequestBody UserDTOResponse userDTO);

    @PutMapping("/address")
    AddressDTOResponse updateAddress(@RequestHeader("Authorization") String token,
                                     @RequestParam("id") Long idAddress,
                                     @RequestBody AddressDTOResponse addressDTO);

    @PostMapping("/address")
    AddressDTOResponse registerAddress(@RequestHeader("Authorization") String token,
                                       @RequestBody AddressDTOResponse addressDTO);

    @PutMapping("/phone")
    PhoneDTOResponse updatePhone(@RequestHeader("Authorization") String token,
                                 @RequestParam("id") Long idPhone,
                                 @RequestBody PhoneDTOResponse phoneDTO);

    @PostMapping("/phone")
    PhoneDTOResponse resgisterPhone(@RequestHeader("Authorization") String token,
                                    @RequestBody PhoneDTOResponse phoneDTO);

}
