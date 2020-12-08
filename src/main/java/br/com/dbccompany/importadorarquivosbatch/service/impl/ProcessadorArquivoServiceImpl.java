package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.service.*;
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
        Long quantidadeClientes = consolidadorQuantidadeClientes.consolidar(dadosLeitura.getRegistros());
        Long quantidadeVendedores = consolidadorQuantidadeVendedores.consolidar(dadosLeitura.getRegistros());
        String idVendaMaisCara = consolidadorVendaMaisCara.consolidar(dadosLeitura.getRegistros());
        String nomePiorVendedor = consolidadorPiorVendedor.consolidar(dadosLeitura.getRegistros());
        return new DadosProcessamento(dadosLeitura.getArquivoPath(), quantidadeClientes, quantidadeVendedores, idVendaMaisCara, nomePiorVendedor);
    }
}