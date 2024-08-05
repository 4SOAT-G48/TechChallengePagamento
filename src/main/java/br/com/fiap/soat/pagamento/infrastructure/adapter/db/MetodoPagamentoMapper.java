package br.com.fiap.soat.pagamento.infrastructure.adapter.db;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.infrastructure.adapter.rest.MetodoPagamentoResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MetodoPagamentoMapper {

    public MetodoPagamento toMetodoPagamento(MetodoPagamentoEntity metodoPagamentoEntity) {
        return new MetodoPagamento(
                metodoPagamentoEntity.getId(),
                metodoPagamentoEntity.getNome(),
                metodoPagamentoEntity.getTipoPagamento(),
                metodoPagamentoEntity.getUrlImagem()
        );
    }

    public MetodoPagamentoEntity toMetodoPagamentoEntity(MetodoPagamento metodoPagamento) {
        return new MetodoPagamentoEntity(
                metodoPagamento.getId(),
                metodoPagamento.getNome(),
                metodoPagamento.getTipoPagamento(),
                metodoPagamento.getUrlImagem()
        );
    }

    public List<MetodoPagamentoResponse> toListResponse(List<MetodoPagamento> metodoPagamentos) {
        return metodoPagamentos.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public MetodoPagamentoResponse toResponse(MetodoPagamento metodoPagamento) {
        return new MetodoPagamentoResponse(metodoPagamento.getId(), metodoPagamento.getNome(), metodoPagamento.getTipoPagamento(), metodoPagamento.getUrlImagem());
    }
}
