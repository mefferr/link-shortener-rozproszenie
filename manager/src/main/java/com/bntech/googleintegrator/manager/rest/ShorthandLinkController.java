package com.bntech.googleintegrator.manager.rest;

import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkDto;
import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkRequest;
import com.bntech.googleintegrator.manager.service.ShorthandLinkServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shorthands")
public class ShorthandLinkController {

    private final ShorthandLinkServiceImpl shorthandService;

    @PostMapping
    public ResponseEntity<ShorthandLinkDto> saveShorthand(@RequestBody ShorthandLinkRequest requestBody) {
        return ResponseEntity.ok(shorthandService.makeShorthandLink(requestBody));
    }
}
