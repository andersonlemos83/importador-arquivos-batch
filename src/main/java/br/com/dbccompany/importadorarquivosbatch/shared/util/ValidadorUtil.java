package br.com.dbccompany.importadorarquivosbatch.shared.util;

import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoOuDiretorioNaoEncontradoException;

import java.io.File;

public final class ValidadorUtil {

    private ValidadorUtil() {
    }

    public static void verificarSeArquivoOuDiretorioExiste(String arquivoOuDiretorio) {
        File arquivoOuDiretorioFile = new File(arquivoOuDiretorio);
        verificarSeArquivoOuDiretorioExiste(arquivoOuDiretorioFile);
    }

    public static void verificarSeArquivoOuDiretorioExiste(File arquivoOuDiretorio) {
        if (!arquivoOuDiretorio.exists()) {
            throw new ArquivoOuDiretorioNaoEncontradoException(arquivoOuDiretorio.getAbsoluteFile());
        }
    }
}