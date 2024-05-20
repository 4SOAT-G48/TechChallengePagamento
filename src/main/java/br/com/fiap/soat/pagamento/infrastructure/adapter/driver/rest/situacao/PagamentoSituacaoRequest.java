package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest.situacao;

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
}
