package br.com.fiap.soat.pagamento.infrastructure.adapter.db;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pagamentos")
public class PagamentoEntity {

    @Id
    private String id;

    private UUID codigo;
    private String pedidoId;

    private String clientId;

    private SituacaoPagamento situacaoPagamento;

    private MetodoPagamento metodoPagamento;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
    private String status;

    public PagamentoEntity(String id, BigDecimal valor, LocalDateTime dataPagamento, String status) {
        this.id = id;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public UUID getCodigo() { return this.codigo; }

    public String getPedidoId() { return this.pedidoId; }

    public SituacaoPagamento getSituacaoPagamento() { return this.situacaoPagamento; }

    public double getValor() {
        return this.valor.doubleValue();
    }

    public String getStatus() {
        return this.status;
    }

    public String getClientId() { return this.clientId; }

    public MetodoPagamento getMetodoPagamento() { return this.metodoPagamento; }

}
