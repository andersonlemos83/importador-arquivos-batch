package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

public class InformacaoException extends RuntimeException {

    public InformacaoException(String mensagem) {
        super(mensagem);
    }
}