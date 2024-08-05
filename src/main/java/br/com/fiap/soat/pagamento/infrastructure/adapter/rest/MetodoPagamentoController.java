package br.com.fiap.soat.pagamento.infrastructure.adapter.rest;

import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.application.service.port.in.IMetodoPagamentoPort;
import br.com.fiap.soat.pagamento.infrastructure.adapter.db.MetodoPagamentoMapper;
import br.com.fiap.soat.pagamento.infrastructure.adapter.db.MetodoPagamentoMongoRepository;
import br.com.fiap.soat.pagamento.infrastructure.adapter.db.PagamentoMapper;
import br.com.fiap.soat.pagamento.infrastructure.adapter.db.PagamentoRepository;
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

import java.util.List;

@Tag(name = "Método de Pagamento", description = "Endpoints relacionados com métodos de pagamentos")
@RestController
@RequestMapping("/api/metodo-pagamento")
public class MetodoPagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private MetodoPagamentoMongoRepository metodoPagamentoRepository;

    @Autowired
    private IMetodoPagamentoPort metodoPagamentoPort;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Autowired
    private MetodoPagamentoMapper metodoPagamentoMapper;

    public MetodoPagamentoController(IMetodoPagamentoPort metodoPagamentoPort, PagamentoMapper pagamentoMapper, MetodoPagamentoMapper metodoPagamentoMapper) {
        this.metodoPagamentoPort = metodoPagamentoPort;
        this.pagamentoMapper = pagamentoMapper;
        this.metodoPagamentoMapper = metodoPagamentoMapper;
    }

    @Operation(summary = "Consultar método de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso", content = @Content(schema = @Schema(implementation = MetodoPagamentoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Método de pagamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagamentoResponse> consultarMetodoPagamento(@PathVariable String id) {
        return metodoPagamentoRepository.findById(id)
                .map(metodoPagamento -> ResponseEntity.ok(new MetodoPagamentoResponse(metodoPagamento.getId(),metodoPagamento.getNome(), metodoPagamento.getTipoPagamento(), metodoPagamento.getUrlImagem())))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Recupera lista de metodos de pagamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metodos de pagamentos encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetodoPagamentoResponse.class)
                    )
                    }
            )
    })
    @GetMapping
    public ResponseEntity<List<MetodoPagamentoResponse>> getMetodos() {
        List<MetodoPagamento> metodoPagamentos = this.metodoPagamentoPort.buscarMetodosPagamentos();
        List<MetodoPagamentoResponse> responseList = this.metodoPagamentoMapper.toListResponse(metodoPagamentos);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
