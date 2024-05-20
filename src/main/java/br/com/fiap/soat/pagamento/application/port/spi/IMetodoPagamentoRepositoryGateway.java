package br.com.fiap.soat.pagamento.application.port.spi;

import br.com.fiap.soat.pagamento.application.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.model.TipoPagamento;

import java.util.List;
import java.util.UUID;

public interface IMetodoPagamentoRepositoryGateway {
    long buscarQuantidade();

    List<MetodoPagamento> buscarTodos();

    MetodoPagamento buscarPeloCodigo(UUID codigo);

    MetodoPagamento salvar(UUID codigo, String nome, TipoPagamento tipoPagamento, String urlImagem);
}
