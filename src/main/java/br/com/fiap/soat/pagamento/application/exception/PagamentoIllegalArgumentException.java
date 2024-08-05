package br.com.fiap.soat.pagamento.application.exception;

import br.com.fiap.soat.pagamento.commons.exception.ApplicationException;

public class PagamentoIllegalArgumentException extends ApplicationException {

    public PagamentoIllegalArgumentException(String message) {
        super(message);
    }
}
