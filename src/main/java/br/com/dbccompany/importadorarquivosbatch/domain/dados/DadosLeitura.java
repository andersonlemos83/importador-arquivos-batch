package br.com.dbccompany.importadorarquivosbatch.domain.dados;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;

import java.nio.file.Path;
import java.util.List;

public class DadosLeitura {

    private Path arquivoPath;
    private List<Registro> registros;

    public Path getArquivoPath() {
        return arquivoPath;
    }

    public void setArquivoPath(Path arquivoPath) {
        this.arquivoPath = arquivoPath;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    @Override
    public String toString() {
        return "DadosLeitura{" +
                "arquivoPath=" + arquivoPath +
                ", registros=" + registros +
                '}';
    }
}