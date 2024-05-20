
package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagamentoResponse {
    private String id;
    private String descricao;

    public String getDescricao() {
        return this.descricao;
    }
}
