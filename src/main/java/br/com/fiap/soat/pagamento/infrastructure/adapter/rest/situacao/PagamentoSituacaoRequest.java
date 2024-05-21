package br.com.fiap.soat.pagamento.infrastructure.adapter.rest.situacao;

import br.com.fiap.soat.pagamento.application.domain.model.SituacaoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoSituacaoRequest {
    private UUID codigo;
    private String situacaoPagamento;

    public UUID getCodigo() {
        return codigo;
    }

    public String getSituacaoPagamento() { return situacaoPagamento; }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public void setSituacaoPagamento(SituacaoPagamento situacaoPagamento) {
        this.situacaoPagamento = String.valueOf(situacaoPagamento);
    }
}
