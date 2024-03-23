package com.bntech.googleintegrator.manager.service;

import com.bntech.googleintegrator.manager.data.repository.ShorthandLinkRepository;
import com.bntech.googleintegrator.manager.exception.ShorthandNotFoundException;
import com.bntech.googleintegrator.manager.mapper.ShorthandLinkDtoMapper;
import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShorthandLinkServiceImpl implements ShorthandLinkService {

    private final ShorthandLinkRepository repository;
    private final ShorthandLinkDtoMapper mapper;

    @Override
    public ShorthandLinkDto getShorthandLink(String id) {
        return mapper.shorthandLinkToShorthandLinkDto(repository.findById(id)
                .orElseThrow(ShorthandNotFoundException::new));
    }
}
