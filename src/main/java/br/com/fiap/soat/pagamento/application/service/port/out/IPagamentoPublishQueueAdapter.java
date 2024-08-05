package br.com.fiap.soat.pagamento.application.service.port.out;

public interface IPagamentoPublishQueueAdapter {

    void publishPagamentoAprovado(String message);

    void publishPagamentoReprovado(String message);

    void publishPagamentoCriado(String message);

    void publishPagamentoAguardandoPagamento(String message);
}
