package br.com.fiap.soat.pagamento.application.domain.model;

public enum SituacaoPagamento {

    /**
     * Pedido acabou de ser criado
     */
    PENDENTE,
    /**
     * Enviado para a operadora que fará o a gestão do pagamento
     * ou gerado o código para pagamento
     */
    AGUARDANDO_PAGAMENTO,

    /**
     * Pagamento confirmado
     */
    APROVADO,
    PAGO,
    /**
     * qualquer outra situação diferente de aprovado
     */
    REPROVADO
}
