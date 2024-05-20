package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.pagamento.application.model.MetodoPagamento;

public class PagamentoCriacaoRequest {
    private MetodoPagamento metodoPagamento;

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
