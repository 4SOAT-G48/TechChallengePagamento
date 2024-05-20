package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.pagamento.application.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.model.Pagamento;
import br.com.fiap.soat.pagamento.application.model.SituacaoPagamento;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PagamentoDTOMapper {

    public Pagamento toPagamento(PagamentoRequest request) {
        MetodoPagamento metodoPagamento = request.getMetodoPagamento();
        return new Pagamento(null, request.getCodigo(), request.getClienteId(), request.getPedidoId(), request.getValor(), SituacaoPagamento.valueOf(request.getSituacaoPagamento()), metodoPagamento);
    }

    public MetodoPagamento toMetodoPagamento(MetodoPagamentoRequest request) {
        return new MetodoPagamento(request.getCodigo(), request.getNome(), request.getTipoPagamento(), request.getUrlImagem());
    }

    public Pagamento toPagamentoCriacao(PagamentoCriacaoRequest request) {
        MetodoPagamento metodoPagamento = request.getMetodoPagamento();
        return new Pagamento(null,null, null, null, 0.0, null, metodoPagamento);
    }

    public MetodoPagamento toMetodoPagamentoCriacao(MetodoPagamentoConsultaRequest request) {
        if (Objects.isNull(request)) {
            return null;
        }
        return new MetodoPagamento(request.getCodigo(), null, null, null);
    }
}
