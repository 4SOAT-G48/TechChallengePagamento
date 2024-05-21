package br.com.fiap.soat.pagamento.application.service;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.infrastructure.adapter.db.PagamentoMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class PagamentoServiceTest {

    @Autowired
    private PagamentoMongoRepository pagamentoMongoRepository;

    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        pagamentoMongoRepository.deleteAll();
        pagamento = new Pagamento(
                "1",
                UUID.randomUUID(),
                "cliente1",
                "pedido1",
                100.0,
                SituacaoPagamento.PENDENTE,
                new MetodoPagamento(UUID.randomUUID(),"Cartão de Crédito", TipoPagamento.MERCADO_PAGO, null)
        );
        pagamentoMongoRepository.save(pagamento);
    }

    @Test
    void testFindById() {
        Optional<Pagamento> foundPagamento = pagamentoMongoRepository.findById(pagamento.getId());
        assertTrue(foundPagamento.isPresent());
        assertEquals(pagamento.getId(), foundPagamento.get().getId());
    }

    @Test
    void testSavePagamento() {
        Pagamento newPagamento = new Pagamento(
                "2",
                UUID.randomUUID(),
                "cliente2",
                "pedido2",
                200.0,
                SituacaoPagamento.PAGO,
                new MetodoPagamento(UUID.randomUUID(),"Boleto", TipoPagamento.MERCADO_PAGO, null)
        );
        Pagamento savedPagamento = pagamentoMongoRepository.save(newPagamento);
        assertNotNull(savedPagamento);
        assertEquals("2", savedPagamento.getId());
    }

    @Test
    void testDeletePagamento() {
        pagamentoMongoRepository.delete(pagamento);
        Optional<Pagamento> deletedPagamento = pagamentoMongoRepository.findById(pagamento.getId());
        assertFalse(deletedPagamento.isPresent());
    }
}
