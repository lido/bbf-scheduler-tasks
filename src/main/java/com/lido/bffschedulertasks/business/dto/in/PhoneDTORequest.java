package com.lido.bffschedulertasks.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTORequest {

    private String ddd;
    private String number;

}
