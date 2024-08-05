package br.com.fiap.soat.pagamento.infrastructure.adapter.db;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.out.IPagamentoRepositoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Component
public class PagamentoRepository implements IPagamentoRepositoryGateway {

    private final PagamentoMongoRepository pagamentoMongoRepository;

    public PagamentoRepository(PagamentoMongoRepository pagamentoMongoRepository) {
        this.pagamentoMongoRepository = pagamentoMongoRepository;
    }

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
