package br.com.fiap.soat.pagamento.infrastructure.adapter.db;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.out.IMetodoPagamentoRepositoryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MetodoPagamentoRepository implements IMetodoPagamentoRepositoryGateway {

    private final MetodoPagamentoMongoRepository metodoPagamentoMongoRepository;
    private final MetodoPagamentoMapper metodoPagamentoMapper;

    public MetodoPagamentoRepository(MetodoPagamentoMongoRepository springMetodoPagamentoRepository, MetodoPagamentoMapper metodoPagamentoMapper) {
        this.metodoPagamentoMongoRepository = springMetodoPagamentoRepository;
        this.metodoPagamentoMapper = metodoPagamentoMapper;
    }

    @Override
    public long buscarQuantidade() {
        return this.metodoPagamentoMongoRepository.count();
    }

    @Override
    public List<MetodoPagamento> buscarTodos() {
        return this.metodoPagamentoMongoRepository.findAll();
    }

    @Override
    public MetodoPagamento buscarPeloCodigo(String id) {
        return this.metodoPagamentoMongoRepository.findById(id).orElse(null);
    }

    @Override
    public MetodoPagamento salvar(String id, String nome, TipoPagamento tipoPagamento, String urlImagem) {
        MetodoPagamento metodoPagamento;
        if (Objects.isNull(id)) {
            metodoPagamento = new MetodoPagamento(id, nome, tipoPagamento, urlImagem);
        } else {
            metodoPagamento = this.metodoPagamentoMongoRepository.findById(id).get();
            metodoPagamento.setNome(nome);
            metodoPagamento.setTipoPagamento(tipoPagamento);
            metodoPagamento.setUrlImagem(urlImagem);
        }

        return this.metodoPagamentoMongoRepository.save(metodoPagamento);
    }
}
