package br.com.fiap.soat.pagamento.infrastructure.config;

import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageHandler {

    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
