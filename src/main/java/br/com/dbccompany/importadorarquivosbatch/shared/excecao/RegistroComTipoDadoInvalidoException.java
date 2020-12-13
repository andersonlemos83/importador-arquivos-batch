package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

import java.util.Arrays;

import static java.text.MessageFormat.format;

public class RegistroComTipoDadoInvalidoException extends ArquivoInvalidoException {

    private static final String PADRAO_MENSAGEM = "O arquivo possui um registro, {0}, com dados incompatíveis com o layout {1}.";

    public RegistroComTipoDadoInvalidoException(String[] registros, String nomeRegistro) {
        super(format(PADRAO_MENSAGEM, Arrays.asList(registros), nomeRegistro));
    }
}