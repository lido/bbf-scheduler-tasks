package com.lido.bffschedulertasks.controller;

import com.lido.bffschedulertasks.business.PhoneService;
import com.lido.bffschedulertasks.business.dto.in.PhoneDTORequest;
import com.lido.bffschedulertasks.business.dto.out.PhoneDTOResponse;
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
@RequestMapping("/phone")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
@Tag(name = "Phone", description = "Register Phone User")
public class PhoneController {

    private final PhoneService phoneService;

    @PutMapping
    @Operation(summary = "Update Phone data",
            description = "Update  Phone data")
    @ApiResponse(responseCode = "200", description = "Phone updated")
    @ApiResponse(responseCode = "404", description = "Phone not regitered")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<PhoneDTOResponse> updatePhone(@RequestHeader(name = "Authorization", required=false) String token,
                                                        @RequestParam("id") Long idPhone,
                                                        @RequestBody PhoneDTORequest phoneDTO){
        return ResponseEntity.ok(phoneService.updatePhone(token, idPhone, phoneDTO));
    }

    @PostMapping
    @Operation(summary = "Save Phone", description = "Create a new address")
    @ApiResponse(responseCode = "200", description = "Phone registered successfully")
    @ApiResponse(responseCode = "400", description = "Phone already registered")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<PhoneDTOResponse> resgisterPhone(@RequestHeader(name = "Authorization", required=false) String token,
                                                           @RequestBody PhoneDTORequest phoneDTO){
        return ResponseEntity.ok(phoneService.registerPhone(token, phoneDTO));
    }
}

