package br.com.fiap.soat.pagamento.application.domain.model;

import br.com.fiap.soat.pagamento.utils.PagamentoHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PagamentoTest {

    @Nested
    class deveCriarPagamento {

        @Test
        void viaConstructor() {
            // Arrange & Act
            Pagamento pagamento = new Pagamento();

            // Assert
            assertNull(pagamento.getId());
        }

        @Test
        void viaSetter() {
            // Arrange
            Pagamento pagamento = new Pagamento();
            UUID codigo = UUID.randomUUID();
            pagamento.setCodigo(codigo);

            //Act & Assert
            assertEquals(codigo, pagamento.getCodigo());
        }

        @Test
        void viaBuilder() {
            // Arrange
            Pagamento pagamento = PagamentoHelper.gerarPagamento();
            UUID codigo = UUID.randomUUID();
            pagamento.setCodigo(codigo);

            //Act & Assert
            assertEquals(codigo, pagamento.getCodigo());
            assertEquals(LocalDateTime.now(), pagamento.getDataPagamento());
            assertEquals("Boleto", pagamento.getMetodoPagamento());
            assertEquals(10.0, pagamento.getValor());
            assertEquals(SituacaoPagamento.APROVADO, pagamento.getSituacaoPagamento());
            assertEquals("abc123", pagamento.getClienteId());
            assertEquals("cba123", pagamento.getPedidoId());
        }
    }

    @Nested
    class deveAtualizarPagamento {
        @Test
        void testAtualizarClientId() {
            // Arrange
            Pagamento pagamento = PagamentoHelper.gerarPagamento();

            // Act
            pagamento.setClienteId("aaa123");

            // Assert
            assertEquals("aaa123", pagamento.getClienteId());
        }

        @Test
        void testAtualizarPedidoId() {
            // Arrange
            Pagamento pagamento = PagamentoHelper.gerarPagamento();

            // Act
            pagamento.setPedidoId("aaa123");

            // Assert
            assertEquals("aaa123", pagamento.getPedidoId());
        }

        @Test
        void testAtualizarValor() {
            // Arrange
            Pagamento pagamento = PagamentoHelper.gerarPagamento();

            // Act
            pagamento.setValor(BigDecimal.valueOf(15.0));

            // Assert
            assertEquals(15.0, pagamento.getValor());
        }

        @Test
        void testAtualizarDataPagamento() {
            // Arrange
            Pagamento pagamento = PagamentoHelper.gerarPagamento();

            // Act
            pagamento.setDataPagamento(LocalDateTime.of(2024, 3, 4, 2, 1));

            // Assert
            assertEquals(LocalDateTime.of(2024, 3, 4, 2, 1), pagamento.getDataPagamento());
        }

        @Test
        void testAtualizarSituacaoPagamento() {
            // Arrange
            Pagamento pagamento = PagamentoHelper.gerarPagamento();

            // Act
            pagamento.setSituacaoPagamento(SituacaoPagamento.PENDENTE);

            // Assert
            assertEquals(SituacaoPagamento.PENDENTE, pagamento.getSituacaoPagamento());
        }

        @Test
        void testAtualizarMetodoPagamento() {
            // Arrange
            Pagamento pagamento = PagamentoHelper.gerarPagamento();

            MetodoPagamento metodoPagamento = new MetodoPagamento(UUID.randomUUID().toString(), "PIX", TipoPagamento.MERCADO_PAGO, "url");

            // Act
            pagamento.setMetodoPagamento(metodoPagamento);

            // Assert
            assertEquals(metodoPagamento, pagamento.getMetodoPagamento());
        }
    }
}
