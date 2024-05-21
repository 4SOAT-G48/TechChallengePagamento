package br.com.fiap.soat.pagamento.infrastructure.adapter.db;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetodoPagamentoRepository extends MongoRepository<MetodoPagamento, String> {
}
