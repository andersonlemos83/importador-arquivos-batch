package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

public class RepositorioException extends RuntimeException {

    public RepositorioException(Throwable excecao) {
        super(excecao);
    }
}