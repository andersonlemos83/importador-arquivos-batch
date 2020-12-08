package br.com.dbccompany.importadorarquivosbatch.domain;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;

import java.nio.file.Path;
import java.util.List;

public class DadosLeitura {

    private final Path arquivoPath;
    private final List<Registro> registros;

    public DadosLeitura(Path arquivoPath, List<Registro> registros) {
        this.arquivoPath = arquivoPath;
        this.registros = registros;
    }

    public Path getArquivoPath() {
        return arquivoPath;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    @Override
    public String toString() {
        return "DadosLeitura{" +
                "arquivoPath=" + arquivoPath +
                ", registros=" + registros +
                '}';
    }
}