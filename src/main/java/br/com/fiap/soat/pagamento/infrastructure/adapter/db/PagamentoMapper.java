package br.com.fiap.soat.pagamento.infrastructure.adapter.db;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    private final MetodoPagamentoMapper metodoPagamentoMapper;

    @Autowired
    public PagamentoMapper(MetodoPagamentoMapper metodoPagamentoMapper) {
        this.metodoPagamentoMapper = metodoPagamentoMapper;
    }

    public Pagamento toPagamento(PagamentoEntity pagamentoEntity) {
        return new Pagamento(
                pagamentoEntity.getId(),
                pagamentoEntity.getCodigo(),
                pagamentoEntity.getClientId(),
                pagamentoEntity.getPedidoId(),
                pagamentoEntity.getValor(),
                pagamentoEntity.getSituacaoPagamento(),
                pagamentoEntity.getMetodoPagamento()
        );
    }

    public PagamentoEntity toPagamentoEntity(Pagamento pagamento) {
        return new PagamentoEntity(
                pagamento.getId(),
                pagamento.getValor(),
                pagamento.getDataPagamento(),
                pagamento.getSituacaoPagamento().toString()
        );
    }
}
