package br.com.fiap.soat.pagamento.application.service.port.out;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;

import java.util.List;
import java.util.UUID;

public interface IMetodoPagamentoRepositoryGateway {
    long buscarQuantidade();

    List<MetodoPagamento> buscarTodos();

    MetodoPagamento buscarPeloCodigo(UUID codigo);

    MetodoPagamento salvar(UUID codigo, String nome, TipoPagamento tipoPagamento, String urlImagem);
}
