package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

import static java.text.MessageFormat.format;

public class IdInvalidoException extends InformacaoException {

    private static final String PADRAO_MENSAGEM = "O arquivo importado possui um registro com ID inválido: {0}";

    public IdInvalidoException(String id) {
        super(format(PADRAO_MENSAGEM, id));
    }
}