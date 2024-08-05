package br.com.fiap.soat.pagamento.infrastructure.adapter.db;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PagamentoMongoRepository extends MongoRepository<Pagamento, String> {
    List<Pagamento> findBySituacaoPagamento(SituacaoPagamento situacaoPagamento);
}
