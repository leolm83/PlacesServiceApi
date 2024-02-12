package com.leolm.placeservice.domain.services;

import com.leolm.placeservice.domain.mappers.PlaceRequestMapper;
import com.leolm.placeservice.domain.repositories.PlaceRepository;
import com.leolm.placeservice.domain.entities.Place;
import com.leolm.placeservice.dtos.PlaceRequestDto;
import com.leolm.placeservice.dtos.PlaceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    private PlaceRepository placeRepository;

    private PlaceRequestMapper placeRequestMapper;
    @Autowired
    public PlaceService(PlaceRepository placeRepository, PlaceRequestMapper placeRequestMapper) {
        this.placeRepository = placeRepository;
        this.placeRequestMapper = placeRequestMapper;
    }

    public PlaceResponseDto create(PlaceRequestDto placeRequestDto){
        Place place = placeRequestMapper.placeRequestDtoToPlace(placeRequestDto);
        placeRepository.save(place);

        return placeRequestMapper.placeToPlaceResponseDto(place);
    }
}
