package br.com.dbccompany.importadorarquivosbatch.domain;

import java.nio.file.Path;
import java.util.List;

public class DadosImportacao {

    private final List<Path> arquivos;

    public DadosImportacao(List<Path> arquivos) {
        this.arquivos = arquivos;
    }

    public List<Path> getArquivos() {
        return arquivos;
    }
}