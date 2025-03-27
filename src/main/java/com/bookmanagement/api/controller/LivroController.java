package com.bookmanagement.api.controller;

import com.bookmanagement.api.dto.LivroRecordDto;
import com.bookmanagement.api.model.LivroModel;
import com.bookmanagement.api.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/livro")
@Tag(name = "Livro")
public class LivroController {

    @Autowired
    LivroService livroService;

    @PostMapping
    @Operation(summary = "Cria um novo livro no sistema. O livro é enviado no corpo da requisição e será armazenado no banco de dados.", method = "POST")
    public ResponseEntity<Object> save( @NotNull @Valid @RequestBody LivroRecordDto livroRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(new LivroModel(livroRecordDto)));
    }
    @GetMapping
    @Operation(summary = "Retorna uma lista com todos os livros cadastrados no sistema.", method = "GET")
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um livro existente no sistema com base no ID fornecido, permitindo a modificação de seus dados.", method = "PUT")
    public ResponseEntity<Object> update( @PathVariable Long id, @Valid @RequestBody LivroRecordDto livroRecordDto) {
        LivroModel livroModel = new LivroModel(livroRecordDto);
        livroModel.setIdLivro(id);
        return ResponseEntity.status(HttpStatus.OK).body(livroService.update(livroModel));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um livro do sistema com base no ID fornecido.", method = "Delete")
    public ResponseEntity<Object> delete( @NotNull @PathVariable Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(livroService.deleteById(id));
    }

}
