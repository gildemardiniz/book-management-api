package com.bookmanagement.api.model;

import com.bookmanagement.api.dto.LivroRecordDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_livro")
public class LivroModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "SEQUENCE_LIVRO", allocationSize = 1)
    @Column(name = "id_livro", nullable = false)
    private Long idLivro;
    private String titulo ;
    private String autor ;
    private Integer anoPublicacao;

    public LivroModel(@NotNull LivroRecordDto livroRecordDto) {
        this.autor = livroRecordDto.autor();
        this.titulo = livroRecordDto.titulo();
        this.anoPublicacao = livroRecordDto.anoPublicacao();
    }
}
