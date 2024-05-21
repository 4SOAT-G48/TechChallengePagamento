package br.com.fiap.soat.pagamento;

import br.com.fiap.soat.pagamento.application.domain.model.TipoPagamento;
import br.com.fiap.soat.pagamento.infrastructure.adapter.rest.MetodoPagamentoController;
import br.com.fiap.soat.pagamento.infrastructure.adapter.rest.MetodoPagamentoResponse;
import br.com.fiap.soat.pagamento.application.domain.model.MetodoPagamento;
import br.com.fiap.soat.pagamento.infrastructure.adapter.db.MetodoPagamentoRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
public class PagamentoStepDefinitions {

    @Autowired
    private MetodoPagamentoController metodoPagamentoController;

    @MockBean
    private MetodoPagamentoRepository metodoPagamentoRepository;

    private ResponseEntity<MetodoPagamentoResponse> response;

    @Given("um método de pagamento com id {string} existe")
    public void um_metodo_de_pagamento_com_id_existe(String id) {
        MetodoPagamento metodoPagamento = new MetodoPagamento(UUID.randomUUID(), null, TipoPagamento.MERCADO_PAGO, null);
        metodoPagamento.setNome("Cartão de Crédito");

        when(metodoPagamentoRepository.findById(id)).thenReturn(Optional.of(metodoPagamento));
    }

    @When("eu recuperar o método de pagamento com id {string}")
    public void eu_recuperar_o_metodo_de_pagamento_com_id(String id) {
        response = metodoPagamentoController.consultarMetodoPagamento(id);
    }

    @Then("a resposta deve ter status {int}")
    public void a_resposta_deve_ter_status(Integer status) {
        assertEquals(status.intValue(), response.getStatusCodeValue());
    }

    @Then("a resposta deve conter os detalhes do método de pagamento")
    public void a_resposta_deve_conter_os_detalhes_do_metodo_de_pagamento() {
        assertNotNull(response.getBody());
//        assertEquals("Cartão de Crédito", response.getBody().getDescricao());
    }

    @Given("nenhum método de pagamento com id {string} existe")
    public void nenhum_metodo_de_pagamento_com_id_existe(String id) {
        when(metodoPagamentoRepository.findById(id)).thenReturn(Optional.empty());
    }
}
