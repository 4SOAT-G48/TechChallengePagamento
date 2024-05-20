package br.com.fiap.soat.pagamento.infrastructure.adapter.driven.persistence.repository;

import br.com.fiap.soat.pagamento.application.model.Pagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PagamentoMongoRepository extends MongoRepository<Pagamento, String> {
}
