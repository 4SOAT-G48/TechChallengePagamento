package br.com.fiap.soat.pagamento.application.service.port.out;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import org.springframework.scheduling.annotation.Async;

public interface IFontePagadoraIntegrationGateway {

    @Async
    void enviaParaFontePagadora(Pagamento pagamento);

    Pagamento buscarSituacaoPagamento(TipoPagamento tipoPagamento, String id);
}
