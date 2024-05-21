package br.com.fiap.soat.pagamento.application.service;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.out.IConsultaInformacaoPagamentoIntegrationGateway;
import br.com.fiap.soat.pagamento.application.service.port.out.IPagamentoRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PagamentoSituacaoUsecaseImplTest {

    @Mock
    private IPagamentoRepositoryGateway pagamentoRepositoryGateway;

    @Mock
    private IConsultaInformacaoPagamentoIntegrationGateway consultaInformacaoPagamentoIntegrationGateway;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PagamentoSituacaoUsecaseImpl pagamentoSituacaoUsecase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAtualizaSituacao_Aprovado() {
        MetodoPagamento metodoPagamento = new MetodoPagamento(UUID.randomUUID(), "PIX", TipoPagamento.MERCADO_PAGO, "url");
        Pagamento pagamentoAntes = new Pagamento(null, UUID.randomUUID(), "abc123", "cba123", 10.0, SituacaoPagamento.AGUARDANDO_PAGAMENTO, metodoPagamento);
        Pagamento pagamentoAtual = new Pagamento(null, UUID.randomUUID(), "abc123", "cba123", 10.0, SituacaoPagamento.APROVADO, metodoPagamento);

        when(pagamentoRepositoryGateway.buscarPeloCodigo(pagamentoAntes.getCodigo())).thenReturn(pagamentoAntes);
        when(pagamentoRepositoryGateway.atualizaSituacao(pagamentoAntes.getCodigo(), SituacaoPagamento.APROVADO)).thenReturn(pagamentoAtual);

        boolean result = pagamentoSituacaoUsecase.atualizaSituacao(pagamentoAtual);

        assertTrue(result);
        verify(pagamentoRepositoryGateway, times(1)).buscarPeloCodigo(pagamentoAntes.getCodigo());
        verify(pagamentoRepositoryGateway, times(1)).atualizaSituacao(pagamentoAntes.getCodigo(), SituacaoPagamento.APROVADO);
        verify(rabbitTemplate, times(1)).convertAndSend("pedido-queue", "Pagamento aprovado: " + pagamentoAtual.getCodigo());
    }

    @Test
    void testAtualizaSituacao_Reprovado() {
        MetodoPagamento metodoPagamento = new MetodoPagamento(UUID.randomUUID(), "PIX", TipoPagamento.MERCADO_PAGO, "url");
        Pagamento pagamentoAntes = new Pagamento(null, UUID.randomUUID(), "abc123", "cba123", 10.0, SituacaoPagamento.AGUARDANDO_PAGAMENTO, metodoPagamento);
        Pagamento pagamentoAtual = new Pagamento(null, UUID.randomUUID(), "abc123", "cba123", 10.0, SituacaoPagamento.REPROVADO, metodoPagamento);

        when(pagamentoRepositoryGateway.buscarPeloCodigo(pagamentoAntes.getCodigo())).thenReturn(pagamentoAntes);
        when(pagamentoRepositoryGateway.atualizaSituacao(pagamentoAntes.getCodigo(), SituacaoPagamento.REPROVADO)).thenReturn(pagamentoAtual);

        boolean result = pagamentoSituacaoUsecase.atualizaSituacao(pagamentoAtual);

        assertTrue(result);
        verify(pagamentoRepositoryGateway, times(1)).buscarPeloCodigo(pagamentoAntes.getCodigo());
        verify(pagamentoRepositoryGateway, times(1)).atualizaSituacao(pagamentoAntes.getCodigo(), SituacaoPagamento.REPROVADO);
        verify(rabbitTemplate, times(1)).convertAndSend("pedido-queue", "Pagamento reprovado: " + pagamentoAtual.getCodigo());
    }

    @Test
    void testBuscarPagamento() {
        UUID codigo = UUID.randomUUID();
        MetodoPagamento metodoPagamento = new MetodoPagamento(UUID.randomUUID(), "PIX", TipoPagamento.MERCADO_PAGO, "url");
        Pagamento pagamento = new Pagamento(null, UUID.randomUUID(), "abc123", "cba123", 10.0, SituacaoPagamento.AGUARDANDO_PAGAMENTO, metodoPagamento);


        when(pagamentoRepositoryGateway.buscarPeloCodigo(codigo)).thenReturn(pagamento);

        Pagamento result = pagamentoSituacaoUsecase.buscarPagamento(codigo);

        assertEquals(pagamento, result);
        verify(pagamentoRepositoryGateway, times(1)).buscarPeloCodigo(codigo);
    }

    @Test
    void testBuscarSituacaoFontePagadora() {
        TipoPagamento tipoPagamento = TipoPagamento.MERCADO_PAGO;
        String id = "123";
        MetodoPagamento metodoPagamento = new MetodoPagamento(UUID.randomUUID(), "PIX", TipoPagamento.MERCADO_PAGO, "url");
        Pagamento pagamento = new Pagamento(null, UUID.randomUUID(), "abc123", "cba123", 10.0, SituacaoPagamento.AGUARDANDO_PAGAMENTO, metodoPagamento);


        when(consultaInformacaoPagamentoIntegrationGateway.buscarSituacaoPagamento(tipoPagamento, id)).thenReturn(pagamento);
        doNothing().when(pagamentoRepositoryGateway).atualizaSituacao(any(UUID.class), any(SituacaoPagamento.class));

        pagamentoSituacaoUsecase.buscarSituacaoFontePagadora(tipoPagamento, id);

        verify(consultaInformacaoPagamentoIntegrationGateway, times(1)).buscarSituacaoPagamento(tipoPagamento, id);
        verify(pagamentoRepositoryGateway, times(1)).atualizaSituacao(any(UUID.class), any(SituacaoPagamento.class));
    }
}
