package br.com.fiap.soat.pagamento.infrastructure.adapter.messaging;

import br.com.fiap.soat.pagamento.application.service.port.out.IPagamentoPublishQueueAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PagamentoPublishQueueAdapter implements IPagamentoPublishQueueAdapter {

    @Value(value = "${message.publish.pagamento.criado.route-key}")
    private String pagamentoCriadoRoutekey;

    @Value(value = "${message.publish.pagamento.criado.exchange}")
    private String pagamentoCriadoExchange;

    @Value(value = "${message.publish.pagamento.aguardando-pagamento.route-key}")
    private String pagamentoAguardandoPagamentoRoutekey;

    @Value(value = "${message.publish.pagamento.aguardando-pagamento.exchange}")
    private String pagamentoAguardandoPagamentoExchange;

    @Value(value = "${message.publish.pagamento.aprovado.route-key}")
    private String pagamentoAprovadoRoutekey;

    @Value(value = "${message.publish.pagamento.aprovado.exchange}")
    private String pagamentoAprovadoExchange;

    @Value(value = "${message.publish.pagamento.reprovado.route-key}")
    private String pagamentoReprovadoRoutekey;

    @Value(value = "${message.publish.pagamento.reprovado.exchange}")
    private String pagamentoReprovadoExchange;

    private RabbitTemplate rabbitTemplate;

    @Override
    public void publishPagamentoCriado(String message) {
        this.rabbitTemplate.convertAndSend(pagamentoCriadoExchange, pagamentoCriadoRoutekey, message);
        log.info("Mensagem enviada para a fila de pagamento criado E:{} R:{} - {}", pagamentoCriadoExchange, pagamentoCriadoRoutekey, message);
    }

    @Override
    public void publishPagamentoAguardandoPagamento(String message) {
        this.rabbitTemplate.convertAndSend(pagamentoAguardandoPagamentoExchange, pagamentoAguardandoPagamentoRoutekey, message);
        log.info("Mensagem enviada para a fila de pagamento aguardando pagamento E:{} R:{} - {}", pagamentoAguardandoPagamentoExchange, pagamentoAguardandoPagamentoRoutekey, message);
    }

    @Override
    public void publishPagamentoAprovado(String message) {
        this.rabbitTemplate.convertAndSend(pagamentoAprovadoExchange, pagamentoAprovadoRoutekey, message);
        log.info("Mensagem enviada para a fila de pagamento aprovado E:{} R:{} - {}", pagamentoAprovadoExchange, pagamentoAprovadoRoutekey, message);
    }

    @Override
    public void publishPagamentoReprovado(String message) {
        this.rabbitTemplate.convertAndSend(pagamentoReprovadoExchange, pagamentoReprovadoRoutekey, message);
        log.info("Mensagem enviada para a fila de pagamento reprovado E:{} R:{} - {}", pagamentoReprovadoExchange, pagamentoReprovadoRoutekey, message);
    }

}
