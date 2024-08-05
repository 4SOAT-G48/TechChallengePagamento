package br.com.fiap.soat.pagamento.application.service;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.application.exception.PagamentoIllegalArgumentException;
import br.com.fiap.soat.pagamento.application.service.port.in.IPagamentoSituacaoPort;
import br.com.fiap.soat.pagamento.application.service.port.out.IFontePagadoraIntegrationGateway;
import br.com.fiap.soat.pagamento.application.service.port.out.IPagamentoPublishQueueAdapter;
import br.com.fiap.soat.pagamento.application.service.port.out.IPagamentoRepositoryGateway;

import java.util.Objects;
import java.util.UUID;

public class PagamentoSituacaoUsecaseImpl implements IPagamentoSituacaoPort {

    private final IPagamentoRepositoryGateway pagamentoRepositoryGateway;
    private final IFontePagadoraIntegrationGateway fontePagadoraIntegrationGateway;
    private final IPagamentoPublishQueueAdapter pagamentoPublishQueueAdapter;

    public PagamentoSituacaoUsecaseImpl(IPagamentoRepositoryGateway pagamentoRepositoryGateway, IFontePagadoraIntegrationGateway consultaInformacaoPagamentoIntegrationGateway, IPagamentoPublishQueueAdapter pagamentoPublishQueueAdapter) {
        this.pagamentoRepositoryGateway = pagamentoRepositoryGateway;
        this.fontePagadoraIntegrationGateway = consultaInformacaoPagamentoIntegrationGateway;
        this.pagamentoPublishQueueAdapter = pagamentoPublishQueueAdapter;
    }


    @Override
    public Pagamento criaPagamento(Pagamento pagamento) throws PagamentoIllegalArgumentException {
        if (Objects.isNull(pagamento.getClienteId())) {
            throw new PagamentoIllegalArgumentException("Cliente não informado.");
        }
        if (Objects.isNull(pagamento.getPedidoId())) {
            throw new PagamentoIllegalArgumentException("Pedido não informado.");
        }
        pagamento.setSituacaoPagamento(SituacaoPagamento.PENDENTE);
        Pagamento salvar = pagamentoRepositoryGateway.salvar(pagamento);
        pagamentoPublishQueueAdapter.publishPagamentoCriado(salvar.toJson());
        return salvar;
    }

    @Override
    public boolean atualizaSituacao(Pagamento pagamento) {
        Pagamento pagamentoAntes = this.pagamentoRepositoryGateway.buscarPeloCodigo(pagamento.getCodigo());
        if (pagamentoAntes.getSituacaoPagamento() != SituacaoPagamento.APROVADO
            || pagamentoAntes.getSituacaoPagamento() != SituacaoPagamento.REPROVADO) {

            Pagamento pagamentoAtual = this.pagamentoRepositoryGateway.atualizaSituacao(pagamentoAntes.getCodigo(), pagamento.getSituacaoPagamento());

            if (pagamentoAntes.getSituacaoPagamento().equals(SituacaoPagamento.PENDENTE)
                && pagamentoAtual.getSituacaoPagamento().equals(SituacaoPagamento.AGUARDANDO_PAGAMENTO)) {
                fontePagadoraIntegrationGateway.enviaParaFontePagadora(pagamentoAtual);
                pagamentoPublishQueueAdapter.publishPagamentoAguardandoPagamento(pagamentoAtual.toJson());

            } else if (pagamentoAntes.getSituacaoPagamento().equals(SituacaoPagamento.AGUARDANDO_PAGAMENTO)
                && pagamentoAtual.getSituacaoPagamento().equals(SituacaoPagamento.APROVADO)) {
                pagamentoPublishQueueAdapter.publishPagamentoAprovado(pagamentoAtual.toJson());
            } else if (pagamentoAntes.getSituacaoPagamento().equals(SituacaoPagamento.AGUARDANDO_PAGAMENTO)
                && pagamentoAtual.getSituacaoPagamento().equals(SituacaoPagamento.REPROVADO)) {
                pagamentoPublishQueueAdapter.publishPagamentoReprovado(pagamentoAtual.toJson());
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
        Pagamento pagamento = this.fontePagadoraIntegrationGateway.buscarSituacaoPagamento(tipoPagamento, id);
        this.atualizaSituacao(pagamento);
    }
}
