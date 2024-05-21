package br.com.fiap.soat.pagamento.application.service.port.out;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;

public interface IConsultaInformacaoPagamentoIntegrationGateway {

    Pagamento buscarSituacaoPagamento(TipoPagamento tipoPagamento, String id);
}
