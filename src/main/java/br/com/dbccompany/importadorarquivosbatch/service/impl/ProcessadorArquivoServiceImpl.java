package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosProcessamentoBuilder;
import br.com.dbccompany.importadorarquivosbatch.service.ProcessadorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorPiorVendedor;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeClientes;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeVendedores;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorVendaMaisCara;
import org.springframework.stereotype.Service;

@Service
public class ProcessadorArquivoServiceImpl implements ProcessadorArquivoService {

    private final ConsolidadorQuantidadeClientes consolidadorQuantidadeClientes;
    private final ConsolidadorQuantidadeVendedores consolidadorQuantidadeVendedores;
    private final ConsolidadorVendaMaisCara consolidadorVendaMaisCara;
    private final ConsolidadorPiorVendedor consolidadorPiorVendedor;

    public ProcessadorArquivoServiceImpl(ConsolidadorQuantidadeClientes consolidadorQuantidadeClientes,
                                         ConsolidadorQuantidadeVendedores consolidadorQuantidadeVendedores,
                                         ConsolidadorVendaMaisCara consolidadorVendaMaisCara,
                                         ConsolidadorPiorVendedor consolidadorPiorVendedor) {
        this.consolidadorQuantidadeClientes = consolidadorQuantidadeClientes;
        this.consolidadorQuantidadeVendedores = consolidadorQuantidadeVendedores;
        this.consolidadorVendaMaisCara = consolidadorVendaMaisCara;
        this.consolidadorPiorVendedor = consolidadorPiorVendedor;
    }

    @Override
    public DadosProcessamento processar(DadosLeitura dadosLeitura) {
        final Long quantidadeClientes = consolidadorQuantidadeClientes.consolidar(dadosLeitura.getRegistros());
        final Long quantidadeVendedores = consolidadorQuantidadeVendedores.consolidar(dadosLeitura.getRegistros());
        final String idVendaMaisCara = consolidadorVendaMaisCara.consolidar(dadosLeitura.getRegistros());
        final String nomePiorVendedor = consolidadorPiorVendedor.consolidar(dadosLeitura.getRegistros());
        return DadosProcessamentoBuilder.umDadosProcessamento()
                .comArquivoPath(dadosLeitura.getArquivoPath())
                .comQuantidadeClientes(quantidadeClientes)
                .comQuantidadeVendedores(quantidadeVendedores)
                .comIdVendaMaisCara(idVendaMaisCara)
                .comNomePiorVendedor(nomePiorVendedor)
                .build();
    }
}