package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

import java.nio.file.Path;

public class ArquivoInvalidoException extends RuntimeException {

    private transient Path arquivoPath;

    public ArquivoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public Path getArquivoPath() {
        return arquivoPath;
    }

    public void setArquivoPath(Path arquivoPath) {
        this.arquivoPath = arquivoPath;
    }
}