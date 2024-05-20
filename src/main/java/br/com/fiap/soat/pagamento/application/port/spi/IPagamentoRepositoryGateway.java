package br.com.fiap.soat.pagamento.application.port.spi;

import br.com.fiap.soat.pagamento.application.model.Pagamento;
import br.com.fiap.soat.pagamento.application.model.SituacaoPagamento;

import java.util.UUID;

public interface IPagamentoRepositoryGateway {

    Pagamento salvar(Pagamento pagamento);

    Pagamento buscarPeloCodigo(UUID codigo);

    Pagamento atualizaSituacao(UUID codigo, SituacaoPagamento situacaoPagamento);
}
