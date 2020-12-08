package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

import java.util.Arrays;

import static java.text.MessageFormat.format;

public class QuantidadeAtributosInvalidoException extends InformacaoException {

    private static final String PADRAO_MENSAGEM = "A quantidade de atributos do registro {0} é incompatível com os dados de {1}.";

    public QuantidadeAtributosInvalidoException(String[] registros, String nomeRegistro) {
        super(format(PADRAO_MENSAGEM, Arrays.asList(registros), nomeRegistro));
    }
}