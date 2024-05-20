package br.com.fiap.soat.pagamento.application.port.api;

import br.com.fiap.soat.pagamento.application.model.MetodoPagamento;

import java.util.List;

public interface IMetodoPagamentoPort {
    long buscarQuantidade();

    List<MetodoPagamento> buscarMetodosPagamentos();

    MetodoPagamento salvar(MetodoPagamento metodoPagamento);
}
