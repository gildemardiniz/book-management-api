package com.bookmanagement.api.controller;

import com.bookmanagement.api.dto.LivroRecordDto;
import com.bookmanagement.api.model.LivroModel;
import com.bookmanagement.api.service.LivroService;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class LivroControllerTest {

    @InjectMocks
    private LivroController livroController;

    @Mock
    private LivroService livroService;

    private final LivroModel livroTeste = new LivroModel(
            1L,
            "As crônicas de Nárnia - O leão, a feiticeira e o guarda-roupa",
            "C. S. Lewis",
            1950
    );

    private final LivroRecordDto livroRecordDtoTest = new LivroRecordDto(
            "As crônicas de Nárnia - O leão, a feiticeira e o guarda-roupa",
            "C. S. Lewis",
            1950
    );

    @Test
    void aoSalvarLivroRetornarStatusCriadoEObjeto() {
        Mockito.when(livroService.save(Mockito.any(LivroModel.class))).thenReturn(livroTeste);
        ResponseEntity<Object> retorno = livroController.save(livroRecordDtoTest);
        assertNotNull(retorno);
        assertEquals(HttpStatus.CREATED, retorno.getStatusCode());
        assertEquals(livroTeste, retorno.getBody());
    }

    @Test
    void aoSalvarLivroDeveRetornarErroInterno() {
        Mockito.when(livroService.save(Mockito.any(LivroModel.class))).thenThrow(new RuntimeException());
        ResponseEntity<Object> retorno = livroController.save(livroRecordDtoTest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,retorno.getStatusCode());
    }

    @Test
    void aoBuscarTodosOsLivroDeveRetornarErroInterno() {
        Mockito.when(livroService.findAll()).thenThrow(new RuntimeException());
        ResponseEntity<Object> retorno = livroController.findAll();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,retorno.getStatusCode());
    }
    @Test
    void aoBuscarTodosLivrosDeveRetornarStatusOk() {
      Mockito.when(livroService.findAll()).thenReturn(Collections.singletonList(livroTeste));
      ResponseEntity<Object> retorno = livroController.findAll();
      assertEquals(HttpStatus.OK,retorno.getStatusCode());
    }

    @Test
    void atualizarLivroDeveRetornarBadRequest() {
        Mockito.when(livroService.update(Mockito.anyLong(),Mockito.any(LivroRecordDto.class))).thenThrow(new ObjectNotFoundException(1L,"Erro: O ID informado não foi encontrado. Por favor, verifique e tente novamente."));
        ResponseEntity<Object> retorno = livroController.update(1L,livroRecordDtoTest);
        assertEquals(HttpStatus.NOT_FOUND,retorno.getStatusCode());
    }

    @Test
    void aoAtualizarLivroDeveRetornarInternalServerError() {
        Mockito.when(livroService.update(Mockito.anyLong(),Mockito.any(LivroRecordDto.class))).thenThrow(new RuntimeException());
        ResponseEntity<Object> retorno = livroController.update(1L,livroRecordDtoTest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,retorno.getStatusCode());
    }

    @Test
    void aoDeletarLivroDeveRetornarInternalServerError() {
        Mockito.doThrow(new RuntimeException()).when(livroService).deleteById(Mockito.anyLong());
        ResponseEntity<Object> retorno = livroController.delete(1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,retorno.getStatusCode());
    }

    @Test
    void aoDeletarLivroDeveRetornarBadRequest() {
        Mockito.doThrow(new ObjectNotFoundException(1L,"Erro: O ID informado não foi encontrado. Por favor, verifique e tente novamente.")).when(livroService).deleteById(Mockito.anyLong());
        ResponseEntity<Object> retorno = livroController.delete(1L);
        assertEquals(HttpStatus.NOT_FOUND,retorno.getStatusCode());
    }

    @Test
    void aoDeletar() {
        Mockito.doNothing().when(livroService).deleteById(Mockito.anyLong());
        ResponseEntity<Object> retorno = livroController.delete(1L);
        assertEquals(HttpStatus.OK,retorno.getStatusCode());
    }


}