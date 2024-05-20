package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.pagamento.application.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.model.TipoPagamento;
import br.com.fiap.soat.pagamento.infrastructure.adapter.driven.persistence.repository.MetodoPagamentoRepository;
import br.com.fiap.soat.pagamento.infrastructure.adapter.driven.persistence.repository.PagamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetodoPagamentoControllerTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private MetodoPagamentoRepository metodoPagamentoRepository;

    @InjectMocks
    private MetodoPagamentoController metodoPagamentoController;

    @Test
    void testConsultarMetodoPagamento() {
        MetodoPagamento metodoPagamento = new MetodoPagamento(UUID.randomUUID(),null, TipoPagamento.MERCADO_PAGO, null);
        metodoPagamento.setNome("Cartão de Crédito");

        when(metodoPagamentoRepository.findById("1")).thenReturn(Optional.of(metodoPagamento));

        ResponseEntity<MetodoPagamentoResponse> response = metodoPagamentoController.consultarMetodoPagamento("1");

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testConsultarMetodoPagamento_NaoEncontrado() {
        when(metodoPagamentoRepository.findById("1")).thenReturn(Optional.empty());

        ResponseEntity<MetodoPagamentoResponse> response = metodoPagamentoController.consultarMetodoPagamento("1");

        assertEquals(404, response.getStatusCodeValue());
    }
}
