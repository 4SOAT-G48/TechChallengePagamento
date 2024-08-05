package br.com.fiap.soat.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TechChallengePagamento {

    public static void main(String[] args) {
        SpringApplication.run(TechChallengePagamento.class, args);
    }
}
