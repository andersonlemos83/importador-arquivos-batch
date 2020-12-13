package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

import static java.text.MessageFormat.format;

public class RegistroSemLayoutDefinidoException extends ArquivoInvalidoException {

    private static final String PADRAO_MENSAGEM = "O arquivo possui um registro sem layout definido: {0}";

    public RegistroSemLayoutDefinidoException(String id) {
        super(format(PADRAO_MENSAGEM, id));
    }
}