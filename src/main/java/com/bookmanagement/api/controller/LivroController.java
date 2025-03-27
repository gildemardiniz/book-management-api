package com.bookmanagement.api.controller;

import com.bookmanagement.api.dto.LivroRecordDto;
import com.bookmanagement.api.model.LivroModel;
import com.bookmanagement.api.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.ObjectNotFoundException;
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
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(new LivroModel(livroRecordDto)));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: Ocorreu um problema ao tentar salvar ou atualizar os dados. Por favor, tente novamente.");
        }
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista com todos os livros cadastrados no sistema.", method = "GET")
    public ResponseEntity<Object> findAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(livroService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: Ocorreu um problema ao tentar recuperar os dados. Por favor, tente novamente.");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um livro existente no sistema com base no ID fornecido, permitindo a modificação de seus dados.", method = "PUT")
    public ResponseEntity<Object> update( @PathVariable Long id, @Valid @RequestBody LivroRecordDto livroRecordDto) {
        try{
            LivroModel livroModel = new LivroModel(livroRecordDto);
            return ResponseEntity.status(HttpStatus.OK).body(livroService.update(id, livroModel));
        }catch (ObjectNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getEntityName());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: Ocorreu um problema ao tentar salvar ou atualizar os dados. Por favor, tente novamente.");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um livro do sistema com base no ID fornecido.", method = "Delete")
    public ResponseEntity<Object> delete( @NotNull @PathVariable Long id) {
        try{
            livroService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso: O item foi deletado com sucesso.");
        }catch (ObjectNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getEntityName());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: Não foi possível deletar os dados solicitados. Por favor, tente novamente.");
        }
    }

}
