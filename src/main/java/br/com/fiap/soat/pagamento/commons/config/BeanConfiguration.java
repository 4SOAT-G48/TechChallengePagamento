package br.com.fiap.soat.pagamento.commons.config;

import br.com.fiap.soat.pagamento.application.service.MetodoPagamentoUsecaseImpl;
import br.com.fiap.soat.pagamento.application.service.port.in.IMetodoPagamentoPort;
import br.com.fiap.soat.pagamento.application.service.port.in.IPagamentoSituacaoPort;
import br.com.fiap.soat.pagamento.application.service.port.out.IMetodoPagamentoRepositoryGateway;
import br.com.fiap.soat.pagamento.application.service.port.out.IPagamentoPublishQueueAdapter;
import br.com.fiap.soat.pagamento.application.service.port.out.IPagamentoRepositoryGateway;
import br.com.fiap.soat.pagamento.application.service.port.out.IFontePagadoraIntegrationGateway;
import br.com.fiap.soat.pagamento.application.service.PagamentoSituacaoUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    public IPagamentoSituacaoPort pagamentoSituacaoUseCase(
        IPagamentoRepositoryGateway pagamentoRepositoryGateway,
        IFontePagadoraIntegrationGateway consultaInformacaoPagamentoIntegrationGateway,
        IPagamentoPublishQueueAdapter pagamentoPublishQueueAdapter) {
        return new PagamentoSituacaoUsecaseImpl(pagamentoRepositoryGateway, consultaInformacaoPagamentoIntegrationGateway, pagamentoPublishQueueAdapter);
    }

    @Bean
    IMetodoPagamentoPort metodoPagamentoUseCase(IMetodoPagamentoRepositoryGateway metodoPagamentoRepositoryGateway) {
        return new MetodoPagamentoUsecaseImpl(metodoPagamentoRepositoryGateway);
    }

}
