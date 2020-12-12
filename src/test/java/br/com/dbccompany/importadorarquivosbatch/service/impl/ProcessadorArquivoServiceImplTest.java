package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosLeituraBuilder;
import br.com.dbccompany.importadorarquivosbatch.service.ProcessadorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorPiorVendedor;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeClientes;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeVendedores;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorVendaMaisCara;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProcessadorArquivoServiceImplTest {

    private ProcessadorArquivoService processadorArquivoService;

    @Mock
    private ConsolidadorQuantidadeClientes consolidadorQuantidadeClientesMock;

    @Mock
    private ConsolidadorQuantidadeVendedores consolidadorQuantidadeVendedoresMock;

    @Mock
    private ConsolidadorVendaMaisCara consolidadorVendaMaisCaraMock;

    @Mock
    private ConsolidadorPiorVendedor consolidadorPiorVendedorMock;

    private DadosLeitura dadosLeitura;

    @Before
    public void inicializarContexto() {
        processadorArquivoService = new ProcessadorArquivoServiceImpl(consolidadorQuantidadeClientesMock,
                consolidadorQuantidadeVendedoresMock, consolidadorVendaMaisCaraMock, consolidadorPiorVendedorMock);

        dadosLeitura = DadosLeituraBuilder.umDadosLeitura().comRegistros(asList()).build();
    }

    @Test
    public void aoProcessarDeveriaRetonarOhDadosProcessamentoEsperado() {
        Mockito.when(consolidadorQuantidadeClientesMock.consolidar(dadosLeitura.getRegistros())).thenReturn(1l);
        Mockito.when(consolidadorQuantidadeVendedoresMock.consolidar(dadosLeitura.getRegistros())).thenReturn(2l);
        Mockito.when(consolidadorVendaMaisCaraMock.consolidar(dadosLeitura.getRegistros())).thenReturn("10");
        Mockito.when(consolidadorPiorVendedorMock.consolidar(dadosLeitura.getRegistros())).thenReturn("Paulo");

        final DadosProcessamento dadosProcessamento = processadorArquivoService.processar(dadosLeitura);

        assertEquals(1l, dadosProcessamento.getQuantidadeClientes());
        assertEquals(2l, dadosProcessamento.getQuantidadeVendedores());
        assertEquals("10", dadosProcessamento.getIdVendaMaisCara());
        assertEquals("Paulo", dadosProcessamento.getNomePiorVendedor());
    }
}
