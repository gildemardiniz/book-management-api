package com.bookmanagement.api.service;

import com.bookmanagement.api.dto.LivroRecordDto;
import com.bookmanagement.api.model.LivroModel;
import com.bookmanagement.api.repository.LivroRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class LivroServiceTest {

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    private final LivroModel livroTest = new LivroModel(
            1L,
            "Harry Potter e a Pedra Filosofal",
            "J.K. Rowling",
            1997
    );
    private final LivroRecordDto livroRecordDtoTest = new LivroRecordDto(
            "Harry Potter e a Pedra Filosofal",
            "J.K. Rowling",
            1997
    );

    @Test
    void aoSalvarRetornoUmObjetoLivro() {
        Mockito.when(livroRepository.save(Mockito.any())).thenReturn(livroTest);
        var retorno = livroService.save(Mockito.any(LivroModel.class));
        assertNotNull(retorno);
        assertEquals(livroTest, retorno);
    }

    @Test
    void aoBuscarTodosRetornarUmaLista() {

        Mockito.when(livroRepository.findAll()).thenReturn(Collections.singletonList(livroTest));
        var retorno = livroService.findAll();
        assertNotNull(retorno);
        assertEquals(Collections.singletonList(livroTest) , retorno);
        assertEquals(1,retorno.size());
    }

    @Test
    void aoBuscarPorIdRetornarUmObjetoLivro() {
        Mockito.when(livroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(livroTest));
        var retorno = livroService.findById(1L);
        assertNotNull(retorno);
        assertEquals(Optional.of(livroTest), retorno);
    }

    @Test
    void aoAtualizardeveLancarExcecaoQuandoLivroNaoExistir() {
        Mockito.when(livroRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            livroService.update(1L, livroRecordDtoTest);
        });
    }
    @Test
    void AoAtualizarRetornarUmObjetoLivro() {
        Mockito.when(livroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(livroTest));
        Mockito.when(livroService.save(Mockito.any(LivroModel.class))).thenReturn(livroTest);
        var retorno = livroService.update(1L, livroRecordDtoTest);
        assertEquals(livroTest, retorno);
    }

    @Test
    void aoDeletardeveLancarExcecaoQuandoLivroNaoExistir() {
        Mockito.when(livroRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            livroService.deleteById(Mockito.anyLong());
        });
    }

    @Test
    void aoDeletarDeveExecutarComSucessoQuandoLivroExistir() {
        Mockito.when(livroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(livroTest));
        Mockito.doNothing().when(livroRepository).deleteById(Mockito.anyLong());
        livroService.deleteById(1L);
        Mockito.verify(livroRepository, Mockito.times(1)).deleteById(Mockito.anyLong());

    }
}