package br.com.fiap.soat.pagamento.commons.config;

import br.com.fiap.soat.pagamento.application.service.port.in.IPagamentoSituacaoPort;
import br.com.fiap.soat.pagamento.application.service.port.out.IPagamentoRepositoryGateway;
import br.com.fiap.soat.pagamento.application.service.port.out.IConsultaInformacaoPagamentoIntegrationGateway;
import br.com.fiap.soat.pagamento.application.service.PagamentoSituacaoUsecaseImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public IPagamentoRepositoryGateway pagamentoSituacaoPort(IPagamentoRepositoryGateway pagamentoRepositoryGateway) {
        return pagamentoRepositoryGateway;
    }

    @Bean
    public IPagamentoSituacaoPort pagamentoSituacaoPort(
            IPagamentoRepositoryGateway pagamentoRepositoryGateway,
            IConsultaInformacaoPagamentoIntegrationGateway consultaInformacaoPagamentoIntegrationGateway,
            @Qualifier("customRabbitMQMessageHandler") RabbitTemplate rabbitTemplate) {
        return new PagamentoSituacaoUsecaseImpl(pagamentoRepositoryGateway, consultaInformacaoPagamentoIntegrationGateway, rabbitTemplate);
    }
}
