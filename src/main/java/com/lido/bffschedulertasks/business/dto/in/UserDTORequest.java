package com.lido.bffschedulertasks.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTORequest {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<AddressDTORequest> addresses;
    private List<PhoneDTORequest> phones;


}
