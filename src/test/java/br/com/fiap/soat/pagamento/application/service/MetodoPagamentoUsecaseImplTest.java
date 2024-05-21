package br.com.fiap.soat.pagamento.application.service;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.out.IMetodoPagamentoRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MetodoPagamentoUsecaseImplTest {

    @Mock
    private IMetodoPagamentoRepositoryGateway metodoPagamentoRepositoryGateway;

    @InjectMocks
    private MetodoPagamentoUsecaseImpl metodoPagamentoUsecase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarQuantidade() {
        when(metodoPagamentoRepositoryGateway.buscarQuantidade()).thenReturn(10L);

        long quantidade = metodoPagamentoUsecase.buscarQuantidade();

        assertEquals(10L, quantidade);
        verify(metodoPagamentoRepositoryGateway, times(1)).buscarQuantidade();
    }

    @Test
    void testBuscarMetodosPagamentos() {
        MetodoPagamento metodo1 = new MetodoPagamento(UUID.randomUUID(), "nome1", TipoPagamento.MERCADO_PAGO, "url1");
        MetodoPagamento metodo2 = new MetodoPagamento(UUID.randomUUID(), "nome2", TipoPagamento.MERCADO_PAGO, "url2");
        List<MetodoPagamento> metodos = Arrays.asList(metodo1, metodo2);

        when(metodoPagamentoRepositoryGateway.buscarTodos()).thenReturn(metodos);

        List<MetodoPagamento> result = metodoPagamentoUsecase.buscarMetodosPagamentos();

        assertEquals(2, result.size());
        assertEquals(metodo1, result.get(0));
        assertEquals(metodo2, result.get(1));
        verify(metodoPagamentoRepositoryGateway, times(1)).buscarTodos();
    }

    @Test
    void testSalvar() {
        MetodoPagamento metodo = new MetodoPagamento(UUID.randomUUID(), "nome", TipoPagamento.MERCADO_PAGO, "url");
        when(metodoPagamentoRepositoryGateway.salvar(UUID.randomUUID(), "nome", TipoPagamento.MERCADO_PAGO, "url")).thenReturn(metodo);

        MetodoPagamento result = metodoPagamentoUsecase.salvar(metodo);

        assertEquals(metodo, result);
        verify(metodoPagamentoRepositoryGateway, times(1)).salvar(UUID.randomUUID(), "nome", TipoPagamento.MERCADO_PAGO, "url");
    }
}
