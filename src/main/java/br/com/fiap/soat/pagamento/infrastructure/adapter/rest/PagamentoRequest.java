package br.com.fiap.soat.pagamento.infrastructure.adapter.rest;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;

import java.util.UUID;

public class PagamentoRequest {
    private UUID codigo;
    private String clienteId;
    private String pedidoId;
    private double valor;
    private String situacaoPagamento;
    private MetodoPagamento metodoPagamento;

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public UUID getCodigo() {
        return this.codigo;
    }

    public String getSituacaoPagamento() {
        return situacaoPagamento;
    }

    public void setSituacaoPagamento(String situacaoPagamento) {
        this.situacaoPagamento = situacaoPagamento;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
