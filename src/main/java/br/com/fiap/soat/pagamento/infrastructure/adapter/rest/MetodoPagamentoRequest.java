package br.com.fiap.soat.pagamento.infrastructure.adapter.rest;

import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MetodoPagamentoRequest {
    private String id;
    private String nome;
    private TipoPagamento tipoPagamento;
    private String urlImagem;

}
