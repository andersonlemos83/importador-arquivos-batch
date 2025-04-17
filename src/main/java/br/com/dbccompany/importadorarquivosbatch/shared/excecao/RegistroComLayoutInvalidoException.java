package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

import java.util.Arrays;

import static java.text.MessageFormat.format;

public final class RegistroComLayoutInvalidoException extends ArquivoInvalidoException {

    private static final String PADRAO_MENSAGEM = "O arquivo possui um registro, {0}, incompatível com o layout {1}.";

    public RegistroComLayoutInvalidoException(String[] registro, String nomeRegistro) {
        super(format(PADRAO_MENSAGEM, Arrays.asList(registro), nomeRegistro));
    }
}