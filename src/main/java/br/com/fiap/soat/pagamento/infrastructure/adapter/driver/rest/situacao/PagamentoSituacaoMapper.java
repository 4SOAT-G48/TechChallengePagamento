package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest.situacao;

import br.com.fiap.soat.pagamento.application.model.Pagamento;
import br.com.fiap.soat.pagamento.application.model.SituacaoPagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoSituacaoMapper {

    public Pagamento toPagamento(PagamentoSituacaoRequest request) {
        return new Pagamento(null, request.getCodigo(), null, null, 0.0, SituacaoPagamento.valueOf(request.getSituacaoPagamento()), null);
    }

    public PagamentoSituacaoResponse toPagamentoSituacaoResponse(Pagamento pagamento) {
        return new PagamentoSituacaoResponse(pagamento.getCodigo(), pagamento.getSituacaoPagamento().name());
    }
}
