package br.com.fiap.soat.pagamento.utils;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class PagamentoHelper {

    public static Pagamento gerarPagamento() {
        MetodoPagamento metodoPagamento = new MetodoPagamento(UUID.randomUUID().toString(), "PIX", TipoPagamento.MERCADO_PAGO, "url");

        return Pagamento.builder()
            .codigo(UUID.randomUUID())
            .clienteId("abc123")
            .pedidoId("cba123")
            .valor(BigDecimal.valueOf(10.0))
            .dataPagamento(LocalDateTime.now())
            .situacaoPagamento(SituacaoPagamento.APROVADO)
            .metodoPagamento(metodoPagamento)
            .build();
    }

}
