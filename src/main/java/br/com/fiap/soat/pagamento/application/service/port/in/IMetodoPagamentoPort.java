package br.com.fiap.soat.pagamento.application.service.port.in;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;

import java.util.List;

public interface IMetodoPagamentoPort {
    long buscarQuantidade();

    List<MetodoPagamento> buscarMetodosPagamentos();

    MetodoPagamento salvar(MetodoPagamento metodoPagamento);
}
