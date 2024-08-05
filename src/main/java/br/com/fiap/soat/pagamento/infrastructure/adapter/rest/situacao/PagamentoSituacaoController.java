package br.com.fiap.soat.pagamento.infrastructure.adapter.rest.situacao;

import br.com.fiap.soat.pagamento.application.domain.model.Pagamento;
import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.in.IPagamentoSituacaoPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@Tag(name = "Situação de Pagamento", description = "APIs para gerenciamento de situações de pagamento")
@RestController
@RequestMapping("/api/pagamento-situacao")
public class PagamentoSituacaoController {

    private IPagamentoSituacaoPort pagamentoSituacaoPort;

    private PagamentoSituacaoMapper pagamentoSituacaoMapper;

    public PagamentoSituacaoController(IPagamentoSituacaoPort pagamentoSituacaoPort, PagamentoSituacaoMapper pagamentoSituacaoMapper) {
        this.pagamentoSituacaoPort = pagamentoSituacaoPort;
        this.pagamentoSituacaoMapper = pagamentoSituacaoMapper;
    }

    @Operation(summary = "Atualizar situação de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Situação atualizada com sucesso", content = @Content(schema = @Schema(implementation = PagamentoSituacaoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @PutMapping
    public ResponseEntity<?> updateSituacaoPagamento(@RequestBody PagamentoSituacaoRequest request) {
        if (Objects.nonNull(request)) {
            boolean atualizaSituacao = this.pagamentoSituacaoPort.atualizaSituacao(this.pagamentoSituacaoMapper.toPagamento(request));
            if (atualizaSituacao) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você deve informar um corpo com código e situação de pagamentos");
        }
    }

    @Operation(summary = "Recupera a situação do pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado e situação devolvida",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagamentoSituacaoResponse.class))})
    })
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<PagamentoSituacaoResponse> getSituacaoPagamento(@PathVariable UUID codigo) {
        Pagamento pagamento = this.pagamentoSituacaoPort.buscarPagamento(codigo);
        PagamentoSituacaoResponse pagamentoSituacaoResponse = this.pagamentoSituacaoMapper.toPagamentoSituacaoResponse(pagamento);
        return new ResponseEntity<>(pagamentoSituacaoResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/mercadopago/notification")
    public ResponseEntity<?> notificationMercadoPago(@RequestParam String topic, @RequestParam String id) {
        if (Objects.nonNull(topic) && topic.equals("payment")) {
            this.pagamentoSituacaoPort.buscarSituacaoFontePagadora(TipoPagamento.MERCADO_PAGO, id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
