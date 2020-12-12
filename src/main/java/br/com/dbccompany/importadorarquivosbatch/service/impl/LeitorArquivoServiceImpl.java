package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosLeituraBuilder;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.service.LeitorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeitorArquivoServiceImpl implements LeitorArquivoService {

    private final LeitorArquivoRepository leitorArquivoRepository;
    private final ArquivoParse arquivoParse;

    public LeitorArquivoServiceImpl(LeitorArquivoRepository leitorArquivoRepository,
                                    ArquivoParse arquivoParse) {
        this.leitorArquivoRepository = leitorArquivoRepository;
        this.arquivoParse = arquivoParse;
    }

    @Override
    public DadosLeitura lerArquivoNaoImportado() {
        final Arquivo arquivo = leitorArquivoRepository.lerArquivoNaoImportado();
        final List<Registro> registros = arquivoParse.parse(arquivo.getRegistrosArray());
        return DadosLeituraBuilder.umDadosLeitura()
                .comArquivoPath(arquivo.getArquivoPath())
                .comRegistros(registros)
                .build();
    }
}