package br.com.fiap.soat.pagamento.application.port.api;

import br.com.fiap.soat.pagamento.application.model.Pagamento;
import br.com.fiap.soat.pagamento.application.model.TipoPagamento;

import java.util.UUID;

public interface IPagamentoSituacaoPort {

    boolean atualizaSituacao(Pagamento pagamento);

    Pagamento buscarPagamento(UUID codigo);

    void buscarSituacaoFontePagadora(TipoPagamento tipoPagamento, String id);
}
