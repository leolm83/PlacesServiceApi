package com.leolm.placeservice.domain.repositories;
import com.leolm.placeservice.domain.entities.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlaceRepository extends CrudRepository<Place, UUID> {
}
