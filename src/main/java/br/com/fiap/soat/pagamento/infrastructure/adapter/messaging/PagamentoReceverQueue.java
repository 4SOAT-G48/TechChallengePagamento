package br.com.fiap.soat.pagamento.infrastructure.adapter.messaging;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.service.port.in.IPagamentoSituacaoPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Component
public class PagamentoReceverQueue {

    private IPagamentoSituacaoPort pagamentoSituacaoPort;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PagamentoReceverQueue(@Lazy IPagamentoSituacaoPort pagamentoSituacaoPort) {
        this.pagamentoSituacaoPort = pagamentoSituacaoPort;
    }

    @RabbitListener(queues = "${message.recever.cria.queues}")
    public void criaPagamento(@Payload String message) {
        try {
            Map<String, Object> genericObject = objectMapper.readValue(message, Map.class);

            Pagamento pagamento = new Pagamento();
            pagamento.setClienteId((String) genericObject.get("clienteId"));
            pagamento.setPedidoId((String) genericObject.get("id"));
            pagamento.setValor(BigDecimal.valueOf((Double) genericObject.get("total")));
            log.info("Mensagem recebida: {}", genericObject);

            pagamentoSituacaoPort.criaPagamento(pagamento);
        } catch (Exception e) {
            //TODO: Implementar tratamento de erro - enviar para fila de erro
            log.error("Erro ao desserializar mensagem: {}", message, e);
        }
    }
}
