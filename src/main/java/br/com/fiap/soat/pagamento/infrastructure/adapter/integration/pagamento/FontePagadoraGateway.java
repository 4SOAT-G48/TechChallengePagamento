package br.com.fiap.soat.pagamento.infrastructure.adapter.integration.pagamento;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.in.IPagamentoSituacaoPort;
import br.com.fiap.soat.pagamento.application.service.port.out.IFontePagadoraIntegrationGateway;
import br.com.fiap.soat.pagamento.infrastructure.adapter.db.PagamentoMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class FontePagadoraGateway implements IFontePagadoraIntegrationGateway {

    private PagamentoMongoRepository pagamentoMongoRepository;
    private IPagamentoSituacaoPort pagamentoSituacaoPort;
    private Random random = new Random();

    public FontePagadoraGateway(PagamentoMongoRepository pagamentoMongoRepository, @Lazy IPagamentoSituacaoPort pagamentoSituacaoPort) {
        this.pagamentoMongoRepository = pagamentoMongoRepository;
        this.pagamentoSituacaoPort = pagamentoSituacaoPort;
    }

    @Async
    @Override
    public void enviaParaFontePagadora(Pagamento pagamento) {
        try {
            // Simular um processamento demorado
            Thread.sleep(30000); // Simula um processamento de 30 segundos
            pagamentoSituacaoPort.buscarSituacaoFontePagadora(TipoPagamento.MERCADO_PAGO, pagamento.getId());
            log.info("Pagamento enviado para a fonte pagadora: {}", pagamento);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Pagamento buscarSituacaoPagamento(TipoPagamento tipoPagamento, String id) {
        //busca todos os pagamentos que estão aguardando pagamento
        List<Pagamento> bySituacaoPagamento = this.pagamentoMongoRepository.findBySituacaoPagamento(SituacaoPagamento.AGUARDANDO_PAGAMENTO);
        //escolhe um randomicamente
        Pagamento pagamento = bySituacaoPagamento.get(this.random.nextInt(bySituacaoPagamento.size()));

        if (this.random.nextDouble() <= 0.8) {
            pagamento.setSituacaoPagamento(SituacaoPagamento.APROVADO);
            log.info("Pagamento aprovado: {}", pagamento);
        } else {
            pagamento.setSituacaoPagamento(SituacaoPagamento.REPROVADO);
            log.info("Pagamento reprovado: {}", pagamento);
        }

        return pagamento;
    }
}
