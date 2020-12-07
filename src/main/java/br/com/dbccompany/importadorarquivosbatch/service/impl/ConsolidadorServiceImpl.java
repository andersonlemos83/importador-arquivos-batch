package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.domain.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import br.com.dbccompany.importadorarquivosbatch.service.ConsolidadorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsolidadorServiceImpl implements ConsolidadorService {

    private final ArquivoParse arquivoParse;

    public ConsolidadorServiceImpl(ArquivoParse arquivoParse) {
        this.arquivoParse = arquivoParse;
    }

    @Override
    public DadosProcessamento consolidar(DadosLeitura dadosLeitura) {
        List<Registro> dados =  arquivoParse.parse(dadosLeitura.getRegistros());


        return null;
    }
}