package br.com.fiap.soat.pagamento.infrastructure.adapter.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoRabbitMQConfig {

    @Value("${message.recever.exchange}")
    private String pagamentoExchange;

    @Value("${message.recever.cria.route-key}")
    private String pagamentoCriaRoutekey;

    @Value("${message.recever.cria.queues}")
    private String pagamentoCriaQueue;

    @Value("${message.recever.envia.route-key}")
    private String pagamentoEnviaRoutekey;

    @Value("${message.recever.envia.queues}")
    private String pagamentoEnviaQueue;

    @Bean
    public DirectExchange pagamentoExchange() {
        return new DirectExchange(pagamentoExchange);
    }

    @Bean
    public Queue pagamentoCriaQueue() {
        return new Queue(pagamentoCriaQueue, true);
    }

    @Bean
    public Binding pagamentoCriaBinding() {
        return BindingBuilder.bind(pagamentoCriaQueue()).to(pagamentoExchange()).with(pagamentoCriaRoutekey);
    }

    @Bean
    public Queue pagamentoEnviaQueue() {
        return new Queue(pagamentoEnviaQueue, true);
    }

    @Bean
    public Binding pagamentoEnviaBinding() {
        return BindingBuilder.bind(pagamentoEnviaQueue()).to(pagamentoExchange()).with(pagamentoEnviaRoutekey);
    }

}
