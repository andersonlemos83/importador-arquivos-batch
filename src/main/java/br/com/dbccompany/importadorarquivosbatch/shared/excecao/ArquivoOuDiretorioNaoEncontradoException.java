package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

import java.io.File;

import static java.text.MessageFormat.format;

public class ArquivoOuDiretorioNaoEncontradoException extends InformacaoException {

    private static final String PADRAO_MENSAGEM = "O arquivo ou diretório {0} não foi encontrado.";

    public ArquivoOuDiretorioNaoEncontradoException(File caminho) {
        super(format(PADRAO_MENSAGEM, caminho));
    }
}