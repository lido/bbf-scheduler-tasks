package com.lido.bffschedulertasks.controller;

import com.lido.bffschedulertasks.business.AddressService;
import com.lido.bffschedulertasks.business.dto.in.AddressDTORequest;
import com.lido.bffschedulertasks.business.dto.out.AddressDTOResponse;
import com.lido.bffschedulertasks.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
@Tag(name = "Address", description = "Register Address User")
public class AddressController {

    private final AddressService addressService;

    @PutMapping
    @Operation(summary = "Update Address data",
            description = "Update  Address data")
    @ApiResponse(responseCode = "200", description = "Address updated")
    @ApiResponse(responseCode = "404", description = "Address not regitered")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<AddressDTOResponse> updateAddress(@RequestHeader(name = "Authorization", required=false) String token,
                                                            @RequestParam("id") Long idAddress,
                                                            @RequestBody AddressDTORequest addressDTO){
        return ResponseEntity.ok(addressService.updateAddress(token, idAddress, addressDTO));
    }

    @PostMapping
    @Operation(summary = "Save Address", description = "Create a new address")
    @ApiResponse(responseCode = "200", description = "Address saved successfully")
    @ApiResponse(responseCode = "400", description = "Address already registered")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<AddressDTOResponse> registerAddress(@RequestHeader(name = "Authorization", required=false) String token,
                                                              @RequestBody AddressDTORequest addressDTO){
        return ResponseEntity.ok(addressService.registerAddress(token, addressDTO));
    }
}
