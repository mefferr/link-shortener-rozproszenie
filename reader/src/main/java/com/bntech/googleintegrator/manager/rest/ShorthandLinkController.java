package com.bntech.googleintegrator.manager.rest;

import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkDto;
import com.bntech.googleintegrator.manager.service.ShorthandLinkServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shorthands")
public class ShorthandLinkController {

    private final ShorthandLinkServiceImpl shorthandService;

    @GetMapping
    public ResponseEntity<ShorthandLinkDto> getShorthand(String id) {
        return ResponseEntity.ok(shorthandService.getShorthandLink(id));
    }
}
