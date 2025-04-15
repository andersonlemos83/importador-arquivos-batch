package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosLeituraBuilder;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorPiorVendedor;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeClientes;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorQuantidadeVendedores;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.ConsolidadorVendaMaisCara;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ProcessadorArquivoServiceImplTest {

    @InjectMocks
    private ProcessadorArquivoServiceImpl processadorArquivoService;

    @Mock
    private ConsolidadorQuantidadeClientes consolidadorQuantidadeClientesMock;

    @Mock
    private ConsolidadorQuantidadeVendedores consolidadorQuantidadeVendedoresMock;

    @Mock
    private ConsolidadorVendaMaisCara consolidadorVendaMaisCaraMock;

    @Mock
    private ConsolidadorPiorVendedor consolidadorPiorVendedorMock;

    @Test
    public void aoProcessarDeveriaRetonarOhDadosProcessamentoEsperado() {
        DadosLeitura dadosLeitura = DadosLeituraBuilder.umDadosLeitura().comRegistros(emptyList()).build();

        Mockito.when(consolidadorQuantidadeClientesMock.consolidar(dadosLeitura.getRegistros())).thenReturn(1L);
        Mockito.when(consolidadorQuantidadeVendedoresMock.consolidar(dadosLeitura.getRegistros())).thenReturn(2L);
        Mockito.when(consolidadorVendaMaisCaraMock.consolidar(dadosLeitura.getRegistros())).thenReturn("10");
        Mockito.when(consolidadorPiorVendedorMock.consolidar(dadosLeitura.getRegistros())).thenReturn("Paulo");

        DadosProcessamento dadosProcessamento = processadorArquivoService.processar(dadosLeitura);

        assertEquals(1L, dadosProcessamento.getQuantidadeClientes());
        assertEquals(2L, dadosProcessamento.getQuantidadeVendedores());
        assertEquals("10", dadosProcessamento.getIdVendaMaisCara());
        assertEquals("Paulo", dadosProcessamento.getNomePiorVendedor());
    }
}