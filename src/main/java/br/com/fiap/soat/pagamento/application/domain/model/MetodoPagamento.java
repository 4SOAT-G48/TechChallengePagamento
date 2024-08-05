package br.com.fiap.soat.pagamento.application.domain.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "pagamentos")
public class MetodoPagamento {
    @Id
    private String id;
    private String nome;
    private TipoPagamento tipoPagamento;
    private String urlImagem;


}
