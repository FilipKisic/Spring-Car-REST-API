package hr.algebra.springproject.model;

import lombok.Builder;


@Builder
public record CarDTO(Long id, String brand, String model, String color, Integer powerInHp){}
