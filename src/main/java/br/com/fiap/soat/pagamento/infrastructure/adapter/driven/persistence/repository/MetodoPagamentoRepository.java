package br.com.fiap.soat.pagamento.infrastructure.adapter.driven.persistence.repository;

import br.com.fiap.soat.pagamento.application.model.MetodoPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetodoPagamentoRepository extends MongoRepository<MetodoPagamento, String> {
}
