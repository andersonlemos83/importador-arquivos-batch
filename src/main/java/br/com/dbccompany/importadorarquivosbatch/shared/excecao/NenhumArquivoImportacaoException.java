package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

public class NenhumArquivoImportacaoException extends InformacaoException {

    public NenhumArquivoImportacaoException() {
        super("Não existe nenhum arquivo para importação.");
    }
}