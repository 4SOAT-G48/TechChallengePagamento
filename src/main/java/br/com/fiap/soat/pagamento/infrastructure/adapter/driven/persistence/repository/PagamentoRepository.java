package br.com.fiap.soat.pagamento.infrastructure.adapter.driven.persistence.repository;

import br.com.fiap.soat.pagamento.application.model.Pagamento;
import br.com.fiap.soat.pagamento.application.model.SituacaoPagamento;
import br.com.fiap.soat.pagamento.application.port.spi.IPagamentoRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PagamentoRepository implements IPagamentoRepositoryGateway {

    @Autowired
    private PagamentoMongoRepository pagamentoMongoRepository;

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoMongoRepository.save(pagamento);
    }

    @Override
    public Pagamento buscarPeloCodigo(UUID codigo) {
        Optional<Pagamento> pagamento = pagamentoMongoRepository.findById(codigo.toString());
        return pagamento.orElse(null);
    }

    @Override
    public Pagamento atualizaSituacao(UUID codigo, SituacaoPagamento situacaoPagamento) {
        Optional<Pagamento> pagamentoOptional = pagamentoMongoRepository.findById(codigo.toString());
        if (pagamentoOptional.isPresent()) {
            Pagamento pagamento = pagamentoOptional.get();
            pagamento.setSituacaoPagamento(situacaoPagamento);
            return pagamentoMongoRepository.save(pagamento);
        }
        return null;
    }
}
