package hr.algebra.springproject.model;

import lombok.Builder;


@Builder
public record AuthResponse(String accessToken, String refreshToken) {
}
