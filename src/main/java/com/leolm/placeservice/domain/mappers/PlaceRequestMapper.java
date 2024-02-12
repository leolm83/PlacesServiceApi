package com.leolm.placeservice.domain.mappers;

import com.leolm.placeservice.dtos.PlaceRequestDto;
import com.leolm.placeservice.domain.entities.Place;
import com.leolm.placeservice.dtos.PlaceResponseDto;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface PlaceRequestMapper {

    Place placeRequestDtoToPlace(PlaceRequestDto placeRequestDto);

    PlaceResponseDto placeToPlaceResponseDto(Place place);
}
