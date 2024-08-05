package br.com.fiap.soat.pagamento.application.service;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.in.IMetodoPagamentoPort;
import br.com.fiap.soat.pagamento.application.service.port.out.IMetodoPagamentoRepositoryGateway;

import java.util.List;

public class MetodoPagamentoUsecaseImpl implements IMetodoPagamentoPort {

    private final IMetodoPagamentoRepositoryGateway metodoPagamentoRepositoryGateway;

    public MetodoPagamentoUsecaseImpl(IMetodoPagamentoRepositoryGateway metodoPagamentoRepositoryGateway) {
        this.metodoPagamentoRepositoryGateway = metodoPagamentoRepositoryGateway;
    }

    @Override
    public long buscarQuantidade() {
        return this.metodoPagamentoRepositoryGateway.buscarQuantidade();
    }

    @Override
    public List<MetodoPagamento> buscarMetodosPagamentos() {
        return this.metodoPagamentoRepositoryGateway.buscarTodos();
    }

    @Override
    public MetodoPagamento salvar(MetodoPagamento metodoPagamento) {
        return this.metodoPagamentoRepositoryGateway.salvar(metodoPagamento.getId(), metodoPagamento.getNome(), metodoPagamento.getTipoPagamento(), metodoPagamento.getUrlImagem());
    }
}
