package br.com.dbccompany.importadorarquivosbatch.service;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.DadosProcessamento;

public interface ConsolidadorService {

    DadosProcessamento consolidar(DadosLeitura dadosLeitura);

}