package br.com.dbccompany.importadorarquivosbatch.service;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.DadosProcessamento;

public interface ProcessadorArquivoService {

    DadosProcessamento processar(DadosLeitura dadosLeitura);

}