package br.com.fiap.soat.pagamento.application.port.spi;

import br.com.fiap.soat.pagamento.application.model.Pagamento;
import br.com.fiap.soat.pagamento.application.model.TipoPagamento;

public interface IConsultaInformacaoPagamentoIntegrationGateway {

    Pagamento buscarSituacaoPagamento(TipoPagamento tipoPagamento, String id);
}
