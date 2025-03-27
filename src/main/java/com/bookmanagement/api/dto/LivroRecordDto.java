package com.bookmanagement.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRecordDto(
        @NotBlank
        String titulo,
        @NotBlank
        String autor ,
        @NotNull
        Integer anoPublicacao

) {
}
