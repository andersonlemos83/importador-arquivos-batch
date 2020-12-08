package br.com.dbccompany.importadorarquivosbatch.service;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;

public interface ProcessadorArquivoService {

    DadosProcessamento processar(DadosLeitura dadosLeitura);

}