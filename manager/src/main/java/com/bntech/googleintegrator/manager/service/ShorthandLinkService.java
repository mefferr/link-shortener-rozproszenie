package com.bntech.googleintegrator.manager.service;

import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkDto;
import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkRequest;
import org.springframework.stereotype.Service;

@Service
public interface ShorthandLinkService {
 ShorthandLinkDto makeShorthandLink(ShorthandLinkRequest requestBody);
}
