package br.com.fiap.soat.pagamento.infrastructure.adapter.rest.situacao;

import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoSituacaoResponse {
    private UUID codigo;
    private String situacaoPagamento;

    public PagamentoSituacaoResponse(UUID codigo, SituacaoPagamento situacaoPagamento) {
        this.codigo = codigo;
        this.situacaoPagamento = String.valueOf(situacaoPagamento);
    }
}
