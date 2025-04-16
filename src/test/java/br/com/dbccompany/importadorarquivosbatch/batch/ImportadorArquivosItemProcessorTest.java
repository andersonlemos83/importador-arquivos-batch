package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.helper.fixture.DadosProcessamentoFixture;
import br.com.dbccompany.importadorarquivosbatch.service.ProcessadorArquivoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ImportadorArquivosItemProcessorTest {

    @InjectMocks
    private ImportadorArquivosItemProcessor importadorArquivosItemProcessor;

    @Mock
    private ProcessadorArquivoService processadorArquivoServiceMock;

    @Test
    public void aoChamarProcessDadoQueSejaComSucessoDeveriaRetonarOhDadosProcessamentoEsperado() {
        DadosLeitura dadosLeitura = DadosLeitura.builder().build();
        DadosProcessamento dadosProcessamentoEsperado = DadosProcessamentoFixture.umDadosProcessamentoQualquer();
        Mockito.when(processadorArquivoServiceMock.processar(dadosLeitura)).thenReturn(dadosProcessamentoEsperado);
        DadosProcessamento dadosProcessamentoRetornado = importadorArquivosItemProcessor.process(dadosLeitura);
        assertSame(dadosProcessamentoEsperado, dadosProcessamentoRetornado);
    }

    @Test
    public void aoChamarProcessDadoQueSejaLancadoUmaRuntimeExceptionDeveriaRetonarNulo() {
        DadosLeitura dadosLeitura = DadosLeitura.builder().build();
        Mockito.when(processadorArquivoServiceMock.processar(dadosLeitura)).thenThrow(new RuntimeException(""));
        assertNull(importadorArquivosItemProcessor.process(dadosLeitura));
    }
}