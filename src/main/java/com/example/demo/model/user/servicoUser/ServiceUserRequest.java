package com.example.demo.model.user.servicoUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServiceUserRequest(
        @NotBlank
        String name,

        @NotNull
        Integer price
) {
}
