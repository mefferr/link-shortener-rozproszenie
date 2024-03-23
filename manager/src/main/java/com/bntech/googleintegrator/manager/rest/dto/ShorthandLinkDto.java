package com.bntech.googleintegrator.manager.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShorthandLinkDto {
    private String shortLink;
    private String fullLink;
}
