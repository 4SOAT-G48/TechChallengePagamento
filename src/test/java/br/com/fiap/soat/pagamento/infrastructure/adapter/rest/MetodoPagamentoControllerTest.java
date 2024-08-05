package br.com.fiap.soat.pagamento.infrastructure.adapter.rest;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.in.IMetodoPagamentoPort;
import br.com.fiap.soat.pagamento.infrastructure.adapter.db.MetodoPagamentoMapper;
import br.com.fiap.soat.pagamento.infrastructure.adapter.db.MetodoPagamentoMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MetodoPagamentoControllerTest {

    @Mock
    private MetodoPagamentoMongoRepository metodoPagamentoRepository;

    @Mock
    private IMetodoPagamentoPort metodoPagamentoPort;

    @Mock
    private MetodoPagamentoMapper metodoPagamentoMapper;

    @InjectMocks
    private MetodoPagamentoController metodoPagamentoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(metodoPagamentoController).build();
    }

    @Test
    void testConsultarMetodoPagamento_Success() throws Exception {
        MetodoPagamento metodoPagamento = new MetodoPagamento(UUID.randomUUID().toString(), "Metodo 1", TipoPagamento.MERCADO_PAGO, "url1");
        MetodoPagamentoResponse response = new MetodoPagamentoResponse();

        when(metodoPagamentoRepository.findById(anyString())).thenReturn(Optional.of(metodoPagamento));
        when(metodoPagamentoMapper.toResponse(metodoPagamento)).thenReturn(response);

        mockMvc.perform(get("/api/metodo-pagamento/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.codigo").value("1"))
            .andExpect(jsonPath("$.nome").value("Metodo 1"))
            .andExpect(jsonPath("$.tipoPagamento").value("Tipo 1"))
            .andExpect(jsonPath("$.urlImagem").value("url1"));
    }

    @Test
    void testConsultarMetodoPagamento_NotFound() throws Exception {
        when(metodoPagamentoRepository.findById(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/metodo-pagamento/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testGetMetodos() throws Exception {
        String random1 = UUID.randomUUID().toString();
        String random2 = UUID.randomUUID().toString();
        MetodoPagamento metodoPagamento1 = new MetodoPagamento(random1, "nome1", TipoPagamento.MERCADO_PAGO, "url1");
        MetodoPagamento metodoPagamento2 = new MetodoPagamento(random2, "Metodo2", TipoPagamento.MERCADO_PAGO, "url2");
        List<MetodoPagamento> metodoPagamentos = Arrays.asList(metodoPagamento1, metodoPagamento2);
        
        when(metodoPagamentoPort.buscarMetodosPagamentos()).thenReturn(metodoPagamentos);

        mockMvc.perform(get("/api/metodo-pagamento"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].codigo").value("1"))
            .andExpect(jsonPath("$[0].nome").value("Metodo 1"))
            .andExpect(jsonPath("$[0].tipoPagamento").value("Tipo 1"))
            .andExpect(jsonPath("$[0].urlImagem").value("url1"))
            .andExpect(jsonPath("$[1].codigo").value("2"))
            .andExpect(jsonPath("$[1].nome").value("Metodo 2"))
            .andExpect(jsonPath("$[1].tipoPagamento").value("Tipo 2"))
            .andExpect(jsonPath("$[1].urlImagem").value("url2"));
    }
}
