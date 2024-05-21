package br.com.fiap.soat.pagamento.application.domain.model;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class MetodoPagamento {
    private UUID codigo;
    private String nome;
    private TipoPagamento tipoPagamento;
    private String urlImagem;

    public MetodoPagamento(UUID codigo, String nome, TipoPagamento tipoPagamento, String urlImagem) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoPagamento = tipoPagamento;
        this.urlImagem = urlImagem;
    }

    // Getters e Setters
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
