package br.com.dbccompany.importadorarquivosbatch.service;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;

public interface LeitorArquivoService {

    DadosLeitura lerArquivoNaoImportado();

}