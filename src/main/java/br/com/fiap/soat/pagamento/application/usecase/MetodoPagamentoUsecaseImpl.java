package br.com.fiap.soat.pagamento.application.usecase;

import br.com.fiap.soat.pagamento.application.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.port.api.IMetodoPagamentoPort;
import br.com.fiap.soat.pagamento.application.port.spi.IMetodoPagamentoRepositoryGateway;

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
        return this.metodoPagamentoRepositoryGateway.salvar(metodoPagamento.getCodigo(), metodoPagamento.getNome(), metodoPagamento.getTipoPagamento(), metodoPagamento.getUrlImagem());
    }
}
