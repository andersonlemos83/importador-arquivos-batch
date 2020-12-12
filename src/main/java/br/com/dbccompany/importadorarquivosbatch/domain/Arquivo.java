package br.com.dbccompany.importadorarquivosbatch.domain;

import java.nio.file.Path;
import java.util.List;

public class Arquivo {

    private final Path arquivoPath;
    private final List<String[]> registrosArray;

    public Arquivo(Path arquivoPath, List<String[]> registrosArray) {
        this.arquivoPath = arquivoPath;
        this.registrosArray = registrosArray;
    }

    public Path getArquivoPath() {
        return arquivoPath;
    }

    public List<String[]> getRegistrosArray() {
        return registrosArray;
    }

    @Override
    public String toString() {
        return "Arquivo{" +
                "arquivoPath=" + arquivoPath +
                ", registrosArray=" + registrosArray +
                '}';
    }
}