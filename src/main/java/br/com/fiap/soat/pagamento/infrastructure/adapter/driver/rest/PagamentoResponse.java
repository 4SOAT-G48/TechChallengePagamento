package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.pagamento.application.model.SituacaoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagamentoResponse {

    private UUID codigo;
    private MetodoPagamentoResponse metodoPagamento;
    private SituacaoPagamento situacaoPagamento;


}
