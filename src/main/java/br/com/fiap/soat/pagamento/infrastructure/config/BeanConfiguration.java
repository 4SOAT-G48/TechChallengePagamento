package br.com.fiap.soat.pagamento.infrastructure.config;

import br.com.fiap.soat.pagamento.application.port.api.IPagamentoSituacaoPort;
import br.com.fiap.soat.pagamento.application.port.spi.IPagamentoRepositoryGateway;
import br.com.fiap.soat.pagamento.application.port.spi.IConsultaInformacaoPagamentoIntegrationGateway;
import br.com.fiap.soat.pagamento.application.usecase.PagamentoSituacaoUsecaseImpl;
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
