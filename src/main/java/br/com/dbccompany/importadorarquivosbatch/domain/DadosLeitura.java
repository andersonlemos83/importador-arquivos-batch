package br.com.dbccompany.importadorarquivosbatch.domain;

import java.nio.file.Path;
import java.util.List;

public class DadosLeitura {

    private final Path arquivoPath;
    private final List<String[]> registros;

    public DadosLeitura(Path arquivoPath, List<String[]> registros) {
        this.arquivoPath = arquivoPath;
        this.registros = registros;
    }

    public Path getArquivoPath() {
        return arquivoPath;
    }

    public List<String[]> getRegistros() {
        return registros;
    }

    @Override
    public String toString() {
        return "DadosLeitura{" +
                "arquivoPath=" + arquivoPath +
                '}';
    }
}