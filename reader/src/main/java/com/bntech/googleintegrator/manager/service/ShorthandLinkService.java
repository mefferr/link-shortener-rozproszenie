package com.bntech.googleintegrator.manager.service;

import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkDto;
import org.springframework.stereotype.Service;

@Service
public interface ShorthandLinkService {
 ShorthandLinkDto getShorthandLink(String id);
}
