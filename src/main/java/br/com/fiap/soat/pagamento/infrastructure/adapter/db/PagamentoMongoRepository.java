package br.com.fiap.soat.pagamento.infrastructure.adapter.db;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PagamentoMongoRepository extends MongoRepository<Pagamento, String> {
}
