package br.com.dbccompany.importadorarquivosbatch.domain.dados.builder;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;

import java.nio.file.Path;
import java.util.List;

public final class DadosLeituraBuilder {

    private final DadosLeitura dadosLeitura = new DadosLeitura();

    public static DadosLeituraBuilder umDadosLeitura() {
        return new DadosLeituraBuilder();
    }

    public DadosLeituraBuilder comArquivoPath(Path arquivoPath) {
        dadosLeitura.setArquivoPath(arquivoPath);
        return this;
    }

    public DadosLeituraBuilder comRegistros(List<Registro> registros) {
        dadosLeitura.setRegistros(registros);
        return this;
    }

    public DadosLeitura build() {
        return dadosLeitura;
    }
}
