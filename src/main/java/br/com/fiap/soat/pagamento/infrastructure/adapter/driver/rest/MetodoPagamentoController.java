
package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest;

import br.com.fiap.soat.pagamento.infrastructure.adapter.driven.persistence.repository.MetodoPagamentoRepository;
import br.com.fiap.soat.pagamento.infrastructure.adapter.driven.persistence.repository.PagamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metodo-pagamento")
@Tag(name = "Método de Pagamento", description = "APIs para gerenciamento de métodos de pagamento")
public class MetodoPagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private MetodoPagamentoRepository metodoPagamentoRepository;

    @Operation(summary = "Consultar método de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = @Content(schema = @Schema(implementation = MetodoPagamentoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Método de pagamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagamentoResponse> consultarMetodoPagamento(@PathVariable String id) {
        return ResponseEntity.ok(new MetodoPagamentoResponse());
    }
}
