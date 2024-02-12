package com.leolm.placeservice.dtos;

import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public record PlaceResponseDto (String name,String slug ,String state, LocalDateTime createdAt, LocalDateTime updatedAt){}
