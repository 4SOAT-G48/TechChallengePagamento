
package br.com.fiap.soat.pagamento.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create("mongodb://" + System.getenv("MONGO_HOST") + ":" + System.getenv("MONGO_PORT")), System.getenv("MONGO_DB"));
    }
}
