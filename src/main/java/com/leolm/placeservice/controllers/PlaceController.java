package com.leolm.placeservice.controllers;

import com.github.slugify.Slugify;
import com.leolm.placeservice.domain.services.PlaceService;
import com.leolm.placeservice.dtos.PlaceRequestDto;
import com.leolm.placeservice.dtos.PlaceResponseDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private PlaceService placeService;

    private Slugify slugify;

    public PlaceController(PlaceService placeService, Slugify slugify) {
        this.placeService = placeService;
        this.slugify = slugify;
    }

    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PlaceResponseDto.class))
                            })
            })//todo adicionar response code aqui
    public ResponseEntity<PlaceResponseDto> create(@Valid @RequestBody PlaceRequestDto placeRequestDto){
        placeRequestDto.setSlug(slugify.slugify(placeRequestDto.getName()));
        PlaceResponseDto createdPlace = placeService.create(placeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlace);
    }

}
