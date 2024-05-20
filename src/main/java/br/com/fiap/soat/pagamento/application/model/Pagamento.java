package br.com.fiap.soat.pagamento.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "pagamentos")
public class Pagamento {

    @Id
    private String id;
    private UUID codigo;
    private String clienteId;
    private String pedidoId;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
    private SituacaoPagamento situacaoPagamento;
    private MetodoPagamento metodoPagamento;

    // Construtor com argumentos
    public Pagamento(String id, UUID codigo, String clienteId, String pedidoId, double valor, SituacaoPagamento situacaoPagamento, MetodoPagamento metodoPagamento) {
        this.id = id;
        this.codigo = codigo;
        this.clienteId = clienteId;
        this.pedidoId = pedidoId;
        this.valor = BigDecimal.valueOf(valor);
        this.situacaoPagamento = situacaoPagamento;
        this.metodoPagamento = metodoPagamento;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public SituacaoPagamento getSituacaoPagamento() {
        return situacaoPagamento;
    }

    public void setSituacaoPagamento(SituacaoPagamento situacaoPagamento) {
        this.situacaoPagamento = situacaoPagamento;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
