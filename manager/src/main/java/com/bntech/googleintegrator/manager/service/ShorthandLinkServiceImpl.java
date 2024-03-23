package com.bntech.googleintegrator.manager.service;

import com.bntech.googleintegrator.manager.data.persistence.ShorthandLink;
import com.bntech.googleintegrator.manager.data.repository.ShorthandLinkRepository;
import com.bntech.googleintegrator.manager.mapper.ShorthandLinkDtoMapper;
import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkDto;
import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class ShorthandLinkServiceImpl implements ShorthandLinkService {

    private final ShorthandLinkRepository repository;
    private final ShorthandLinkDtoMapper mapper;
    private final Integer ID_LENGTH = 10;
    //todo: change to fetch from app.yml
    private final String READER_URL = "http://localhost:8081/";

    @Override
    public ShorthandLinkDto makeShorthandLink(ShorthandLinkRequest requestBody) {
        Random random = new Random();
        String shorthandId = random.ints(97, 123)
                .limit(ID_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        ShorthandLinkDto generatedLink = ShorthandLinkDto.builder()
                .fullLink(requestBody.getRequest())
                .shortLink(READER_URL + shorthandId)
                .build();
        ShorthandLink newLink = repository.save(mapper.shorthandLinkDtoToShorthandLink(generatedLink));

        return mapper.shorthandLinkToShorthandLinkDto(newLink);
    }
}
