package br.com.fiap.soat.pagamento.application.service.port.out;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;

import java.util.UUID;

public interface IPagamentoRepositoryGateway {

    Pagamento salvar(Pagamento pagamento);

    Pagamento buscarPeloCodigo(UUID codigo);

    Pagamento atualizaSituacao(UUID codigo, SituacaoPagamento situacaoPagamento);
}
