package br.com.fiap.soat.pagamento.application.usecase;

import br.com.fiap.soat.pagamento.application.model.Pagamento;
import br.com.fiap.soat.pagamento.application.model.SituacaoPagamento;
import br.com.fiap.soat.pagamento.application.model.TipoPagamento;
import br.com.fiap.soat.pagamento.application.port.api.IPagamentoSituacaoPort;
import br.com.fiap.soat.pagamento.application.port.spi.IConsultaInformacaoPagamentoIntegrationGateway;
import br.com.fiap.soat.pagamento.application.port.spi.IPagamentoRepositoryGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

public class PagamentoSituacaoUsecaseImpl implements IPagamentoSituacaoPort {

    private final IPagamentoRepositoryGateway pagamentoRepositoryGateway;
    private final IConsultaInformacaoPagamentoIntegrationGateway consultaInformacaoPagamentoIntegrationGateway;
    private final RabbitTemplate rabbitTemplate;

    public PagamentoSituacaoUsecaseImpl(IPagamentoRepositoryGateway pagamentoRepositoryGateway, IConsultaInformacaoPagamentoIntegrationGateway consultaInformacaoPagamentoIntegrationGateway, RabbitTemplate rabbitTemplate) {
        this.pagamentoRepositoryGateway = pagamentoRepositoryGateway;
        this.consultaInformacaoPagamentoIntegrationGateway = consultaInformacaoPagamentoIntegrationGateway;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public boolean atualizaSituacao(Pagamento pagamento) {
        Pagamento pagamentoAntes = this.pagamentoRepositoryGateway.buscarPeloCodigo(pagamento.getCodigo());
        if (pagamentoAntes.getSituacaoPagamento() != SituacaoPagamento.APROVADO
                || pagamentoAntes.getSituacaoPagamento() != SituacaoPagamento.REPROVADO) {

            Pagamento pagamentoAtual = this.pagamentoRepositoryGateway.atualizaSituacao(pagamentoAntes.getCodigo(), pagamento.getSituacaoPagamento());

            if (pagamentoAntes.getSituacaoPagamento().equals(SituacaoPagamento.AGUARDANDO_PAGAMENTO)
                    && pagamentoAtual.getSituacaoPagamento().equals(SituacaoPagamento.APROVADO)) {
                rabbitTemplate.convertAndSend("pedido-queue", "Pagamento aprovado: " + pagamentoAtual.getCodigo());
            } else if (pagamentoAntes.getSituacaoPagamento().equals(SituacaoPagamento.AGUARDANDO_PAGAMENTO)
                    && pagamentoAtual.getSituacaoPagamento().equals(SituacaoPagamento.REPROVADO)) {
                rabbitTemplate.convertAndSend("pedido-queue", "Pagamento reprovado: " + pagamentoAtual.getCodigo());
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Pagamento buscarPagamento(UUID codigo) {
        return this.pagamentoRepositoryGateway.buscarPeloCodigo(codigo);
    }

    @Override
    public void buscarSituacaoFontePagadora(TipoPagamento tipoPagamento, String id) {
        Pagamento pagamento = this.consultaInformacaoPagamentoIntegrationGateway.buscarSituacaoPagamento(tipoPagamento, id);
        this.atualizaSituacao(pagamento);
    }
}
