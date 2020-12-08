package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

import java.util.Arrays;

import static java.text.MessageFormat.format;

public class RegistroParseException extends InformacaoException {

    private static final String PADRAO_MENSAGEM = "Ocorreu um erro inesperado durante o parse do registro {0} para {1}.";

    public RegistroParseException(String[] registros, String nomeRegistro) {
        super(format(PADRAO_MENSAGEM, Arrays.asList(registros), nomeRegistro));
    }
}