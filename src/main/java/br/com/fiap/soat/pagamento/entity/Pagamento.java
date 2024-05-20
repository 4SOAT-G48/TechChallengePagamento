package br.com.fiap.soat.pagamento.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "pagamentos")
public class Pagamento {

    @Id
    private String id;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
    private String status;

    public Pagamento(String id, BigDecimal valor, LocalDateTime dataPagamento, String status) {
        this.id = id;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public String getStatus() {
        return this.status;
    }
}
