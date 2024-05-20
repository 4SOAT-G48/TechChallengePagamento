
# TechChallengePagamento

## Descrição 
Esse projeto isola o serviço `Pagamento` do projeto TechChallenge.

## Requirements
- Docker
- Docker Compose
- Java 11
- Maven

## Setup

1. Crie um arquivo `.env` baseado no `.env.example` e coloque as variaveis necessárias.

2. Crie a Docker image:
    ```sh
    make build
    ```

3. Rode os serviços:
    ```sh
    make run
    ```

## Tests
Para executar os testes:
```sh
make test
```
