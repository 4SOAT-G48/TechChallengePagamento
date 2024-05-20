Feature: Processamento de Pagamento

  Scenario: Recuperação de método de pagamento com sucesso
    Given um método de pagamento com id "1" existe
    When eu recuperar o método de pagamento com id "1"
    Then a resposta deve ter status 200
    And a resposta deve conter os detalhes do método de pagamento

  Scenario: Método de pagamento não encontrado
    Given nenhum método de pagamento com id "2" existe
    When eu recuperar o método de pagamento com id "2"
    Then a resposta deve ter status 404
