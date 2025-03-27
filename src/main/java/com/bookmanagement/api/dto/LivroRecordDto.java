package com.bookmanagement.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRecordDto(
        @NotBlank(message = "Erro: O título não pode estar vazio. Por favor, insira um valor válido.")
        String titulo,
        @NotBlank(message = "Erro: O autor não pode estar vazio. Por favor, insira um valor autor.")
        String autor ,
        @NotNull(message = "Erro: O ano de publicação não pode estar vazio. Por favor, insira um valor válido.")
        Integer anoPublicacao
) {
}
