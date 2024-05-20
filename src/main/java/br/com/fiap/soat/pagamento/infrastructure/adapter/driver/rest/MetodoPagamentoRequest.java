package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.pagamento.application.model.TipoPagamento;

import java.util.UUID;

public class MetodoPagamentoRequest {
    private UUID codigo;
    private String nome;
    private TipoPagamento tipoPagamento;
    private String urlImagem;

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
