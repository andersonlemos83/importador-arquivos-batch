package br.com.dbccompany.importadorarquivosbatch.shared.util;

import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;

import java.io.File;

import static java.text.MessageFormat.format;

public final class ValidadorUtil {

    private ValidadorUtil() {
    }

    public static void verificarSeArquivoOuDiretorioExiste(String arquivoOuDiretorio) {
        File arquivoOuDiretorioFile = new File(arquivoOuDiretorio);
        verificarSeArquivoOuDiretorioExiste(arquivoOuDiretorioFile);
    }

    public static void verificarSeArquivoOuDiretorioExiste(File arquivoOuDiretorio) {
        if (!arquivoOuDiretorio.exists()) {
            final String mensagem = format("O arquivo ou diretório {0} não foi encontrado.", arquivoOuDiretorio.getAbsoluteFile());
            throw new InformacaoException(mensagem);
        }
    }
}
