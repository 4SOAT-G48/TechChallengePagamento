package br.com.fiap.soat.pagamento.application.service.port.in;

public interface IPagamentoReceverQueueAdapter {
    void recebePagamentoInicia(String message);
}
