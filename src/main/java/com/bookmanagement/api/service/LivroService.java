package com.bookmanagement.api.service;

import com.bookmanagement.api.model.LivroModel;
import com.bookmanagement.api.repository.LivroRepository;
import jakarta.validation.constraints.NotNull;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Object update(Long id, LivroModel livroModel) {
        var retorno = this.findById(id);
        if (retorno.isEmpty()) {
            throw new ObjectNotFoundException(id,"Erro: O ID informado não foi encontrado. Por favor, verifique e tente novamente.");
        }
            livroModel.setIdLivro(id);
            return livroRepository.save(livroModel);
    }

    public void deleteById(@NotNull Long id) {
        var retorno = this.findById(id);
        if (retorno.isEmpty()) {
            throw new ObjectNotFoundException(id,"Erro: O ID informado não foi encontrado. Por favor, verifique e tente novamente.");
        }else{
            livroRepository.deleteById(id);
        }
    }
}
