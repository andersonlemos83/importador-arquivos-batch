package br.com.dbccompany.importadorarquivosbatch.domain;

import java.nio.file.Path;
import java.util.List;

public class DadosProcessamento {

    private final List<Path> arquivosProcessados;

    public DadosProcessamento(List<Path> arquivosProcessados) {
        this.arquivosProcessados = arquivosProcessados;
    }

    public List<Path> getArquivosProcessados() {
        return arquivosProcessados;
    }
}