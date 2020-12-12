package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosLeituraBuilder;
import br.com.dbccompany.importadorarquivosbatch.fixture.DadosProcessamentoFixture;
import br.com.dbccompany.importadorarquivosbatch.service.ProcessadorArquivoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorArquivosItemProcessorTest {

    private ImportadorArquivosItemProcessor importadorArquivosItemProcessor;

    @Mock
    private ProcessadorArquivoService processadorArquivoServiceMock;

    private DadosLeitura dadosLeitura;
    private DadosProcessamento dadosProcessamentoEsperado;

    @Before
    public void inicializarContexto() {
        importadorArquivosItemProcessor = new ImportadorArquivosItemProcessor(processadorArquivoServiceMock);

        dadosLeitura = DadosLeituraBuilder.umDadosLeitura().build();
        dadosProcessamentoEsperado = DadosProcessamentoFixture.umDadosProcessamentoQualquer();
    }

    @Test
    public void aoChamarProcessDadoQueSejaComSucessoDeveriaRetonarOhDadosProcessamentoEsperado() {
        Mockito.when(processadorArquivoServiceMock.processar(dadosLeitura)).thenReturn(dadosProcessamentoEsperado);
        final DadosProcessamento dadosProcessamentoRetornado = importadorArquivosItemProcessor.process(dadosLeitura);
        assertSame(dadosProcessamentoEsperado, dadosProcessamentoRetornado);
    }

    @Test
    public void aoChamarProcessDadoQueSejaLancadoUmaRuntimeExceptionDeveriaRetonarNulo() {
        Mockito.when(processadorArquivoServiceMock.processar(dadosLeitura)).thenThrow(new RuntimeException(""));
        assertNull(importadorArquivosItemProcessor.process(dadosLeitura));
    }
}