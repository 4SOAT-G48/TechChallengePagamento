package br.com.fiap.soat.pagamento.application.service.port.in;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;

import java.util.UUID;

public interface IPagamentoSituacaoPort {

    boolean atualizaSituacao(Pagamento pagamento);

    Pagamento buscarPagamento(UUID codigo);

    void buscarSituacaoFontePagadora(TipoPagamento tipoPagamento, String id);
}
