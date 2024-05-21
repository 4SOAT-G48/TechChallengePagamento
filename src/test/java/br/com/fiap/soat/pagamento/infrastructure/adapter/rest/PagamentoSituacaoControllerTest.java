package br.com.fiap.soat.pagamento.infrastructure.adapter.rest.situacao;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.in.IPagamentoSituacaoPort;
import br.com.fiap.soat.pagamento.utils.PagamentoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PagamentoSituacaoControllerTest {

    @Mock
    private IPagamentoSituacaoPort pagamentoSituacaoPort;

    @Mock
    private PagamentoSituacaoMapper pagamentoSituacaoMapper;

    @InjectMocks
    private PagamentoSituacaoController pagamentoSituacaoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoSituacaoController).build();
    }

    @Test
    void testUpdateSituacaoPagamento() throws Exception {
        PagamentoSituacaoRequest request = new PagamentoSituacaoRequest();
        request.setCodigo(UUID.randomUUID());
        request.setSituacaoPagamento(SituacaoPagamento.APROVADO);

        Pagamento pagamento = PagamentoHelper.gerarPagamento();

        when(pagamentoSituacaoMapper.toPagamento(any(PagamentoSituacaoRequest.class))).thenReturn(pagamento);
        when(pagamentoSituacaoPort.atualizaSituacao(any(Pagamento.class))).thenReturn(true);

        mockMvc.perform(put("/api/pagamento-situacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"codigo\": \"" + request.getCodigo() + "\", \"situacaoPagamento\": \"APROVADO\" }"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSituacaoPagamento() throws Exception {
        UUID codigo = UUID.randomUUID();
        Pagamento pagamento = PagamentoHelper.gerarPagamento();
        PagamentoSituacaoResponse response = new PagamentoSituacaoResponse(pagamento.getCodigo(), pagamento.getSituacaoPagamento());

        when(pagamentoSituacaoPort.buscarPagamento(codigo)).thenReturn(pagamento);
        when(pagamentoSituacaoMapper.toPagamentoSituacaoResponse(any(Pagamento.class))).thenReturn(response);

        mockMvc.perform(get("/api/pagamento-situacao/" + codigo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(codigo.toString()))
                .andExpect(jsonPath("$.situacaoPagamento").value(SituacaoPagamento.AGUARDANDO_PAGAMENTO.toString()));
    }

    @Test
    void testNotificationMercadoPago() throws Exception {
        String topic = "payment";
        String id = "123";

        doNothing().when(pagamentoSituacaoPort).buscarSituacaoFontePagadora(TipoPagamento.MERCADO_PAGO, id);

        mockMvc.perform(get("/api/pagamento-situacao/mercadopago/notification")
                        .param("topic", topic)
                        .param("id", id))
                .andExpect(status().isOk());
    }
}
