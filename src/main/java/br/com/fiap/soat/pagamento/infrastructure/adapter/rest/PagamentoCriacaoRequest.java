package br.com.fiap.soat.pagamento.infrastructure.adapter.rest;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;

public class PagamentoCriacaoRequest {
    private MetodoPagamento metodoPagamento;

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
