package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest;

import java.util.UUID;

public class MetodoPagamentoConsultaRequest {
    private UUID codigo;

    // Construtores, Getters e Setters
    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }
}
