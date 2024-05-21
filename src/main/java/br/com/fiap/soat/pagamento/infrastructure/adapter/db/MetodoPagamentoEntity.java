package br.com.fiap.soat.pagamento.infrastructure.adapter.db;

import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "metodos_pagamento")
public class MetodoPagamentoEntity {

    @Id
    private UUID codigo;

    private String nome;

    private TipoPagamento tipoPagamento;

    private String urlImagem;

    @CreatedDate
    private Date dataCriacao;

    @LastModifiedDate
    private Date dataAtualizacao;

    public UUID getCodigo() { return this.codigo; }

    public String getNome() { return this.nome; }

    public TipoPagamento getTipoPagamento() { return this.tipoPagamento; }

    public String getUrlImagem() { return this.urlImagem; }

    public MetodoPagamentoEntity(UUID codigo, String nome, TipoPagamento tipoPagamento, String urlImagem) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoPagamento = tipoPagamento;
        this.urlImagem = urlImagem;
    }

    @PrePersist
    public void insereDatas() {
        if (Objects.isNull(this.dataCriacao)) {
            this.dataCriacao = new Date();
            this.dataAtualizacao = new Date();
        }
    }

    @PreUpdate
    public void atualizaDataAtualizacao() {
        this.dataAtualizacao = new Date();
    }

    public void atualizar(String nome, TipoPagamento tipoPagamento, String urlImagem) {
        this.nome = nome;
        this.tipoPagamento = tipoPagamento;
        this.urlImagem = urlImagem;
        this.dataAtualizacao = new Date();
    }
}
