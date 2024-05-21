package br.com.fiap.soat.pagamento.commons.config;

import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageHandler {

    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
