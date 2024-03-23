package com.bntech.googleintegrator.manager.mapper;

import com.bntech.googleintegrator.manager.data.persistence.ShorthandLink;
import com.bntech.googleintegrator.manager.rest.dto.ShorthandLinkDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShorthandLinkDtoMapper {
    ShorthandLinkDto shorthandLinkToShorthandLinkDto(ShorthandLink shorthandLink);
    ShorthandLink shorthandLinkDtoToShorthandLink(ShorthandLinkDto shorthandLinkDto);
}
