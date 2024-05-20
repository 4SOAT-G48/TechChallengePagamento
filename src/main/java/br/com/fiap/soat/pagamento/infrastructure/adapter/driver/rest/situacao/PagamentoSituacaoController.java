package br.com.fiap.soat.pagamento.infrastructure.adapter.driver.rest.situacao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/pagamento-situacao")
@Tag(name = "Situação de Pagamento", description = "APIs para gerenciamento de situações de pagamento")
public class PagamentoSituacaoController {

    @Operation(summary = "Atualizar situação de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Situação atualizada com sucesso", content = @Content(schema = @Schema(implementation = PagamentoSituacaoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoSituacaoResponse> atualizarSituacaoPagamento(@PathVariable UUID id, @RequestBody PagamentoSituacaoRequest request) {
        return ResponseEntity.ok(new PagamentoSituacaoResponse(id, request.getSituacaoPagamento()));
    }
}
