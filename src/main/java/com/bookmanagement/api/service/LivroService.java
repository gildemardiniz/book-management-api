package com.bookmanagement.api.service;

import com.bookmanagement.api.dto.LivroRecordDto;
import com.bookmanagement.api.model.LivroModel;
import com.bookmanagement.api.repository.LivroRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;

    public Object save(LivroModel livroModel) {
        return livroRepository.save(livroModel);
    }

    public List<LivroModel> findAll() {
        return livroRepository.findAll();
    }

    public Optional<LivroModel> findById(Long id) {
        return livroRepository.findById(id);
    }

    public Object deleteById(@NotNull Long id) {
        var retorno = this.findById(id);
        if (retorno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: O ID informado não foi encontrado. Por favor, verifique e tente novamente.");
        }else{
            livroRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso: O item foi deletado com sucesso.");
        }
    }

    public Object update(LivroModel livroModel) {
        var retorno = this.findById(livroModel.getIdLivro());
        if (retorno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: O ID informado não foi encontrado. Por favor, verifique e tente novamente.");
        }
            return livroRepository.save(livroModel);
    }
}
