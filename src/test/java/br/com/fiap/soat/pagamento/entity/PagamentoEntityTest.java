package br.com.fiap.soat.pagamento.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoEntityTest {

    @Test
    void testPagamentoEntity() {
        Pagamento pagamento = new Pagamento(
                "1",
                BigDecimal.valueOf(100.0),
                LocalDateTime.now(),
                "PENDENTE"
        );

        assertNotNull(pagamento);
        assertEquals("1", pagamento.getId());
        assertEquals(BigDecimal.valueOf(100.0), pagamento.getValor());
        assertEquals("PENDENTE", pagamento.getStatus());
    }
}
