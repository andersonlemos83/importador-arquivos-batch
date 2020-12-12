package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosLeituraBuilder;
import br.com.dbccompany.importadorarquivosbatch.repository.MovedorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.service.LeitorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorArquivosItemReaderTest {

    private ImportadorArquivosItemReader importadorArquivosItemReader;

    @Mock
    private LeitorArquivoService leitorArquivoServiceMock;

    @Mock
    private MovedorArquivoRepository movedorArquivoRepositoryMock;

    private DadosLeitura dadosLeituraEsperado;

    @Before
    public void inicializarContexto() {
        importadorArquivosItemReader = new ImportadorArquivosItemReader(leitorArquivoServiceMock, movedorArquivoRepositoryMock);

        dadosLeituraEsperado = DadosLeituraBuilder.umDadosLeitura().build();
    }

    @Test
    public void aoChamarReadDadoQueSejaComSucessoDeveriaRetonarOhDadosLeituraEsperado() {
        Mockito.when(leitorArquivoServiceMock.lerArquivoNaoImportado()).thenReturn(dadosLeituraEsperado);
        final DadosLeitura dadosLeituraRetornado = importadorArquivosItemReader.read();
        assertSame(dadosLeituraEsperado, dadosLeituraRetornado);
    }

    @Test
    public void aoChamarReadDadoQueSejaLancadoUmaInformacaoExceptionDeveriaRetonarNulo() {
        Mockito.when(leitorArquivoServiceMock.lerArquivoNaoImportado()).thenThrow(new InformacaoException(""));
        assertNull(importadorArquivosItemReader.read());
    }

    @Test
    public void aoChamarReadDadoQueSejaLancadoUmaArquivoInvalidoExceptionDeveriaRetonarNulo() {
        Mockito.when(leitorArquivoServiceMock.lerArquivoNaoImportado()).thenThrow(gerarArquivoInvalidoException());
        assertNull(importadorArquivosItemReader.read());
    }

    @Test
    public void aoChamarReadDadoQueSejaLancadoUmaArquivoInvalidoExceptionDeveriaMoverParaInvalido() {
        Mockito.when(leitorArquivoServiceMock.lerArquivoNaoImportado()).thenThrow(gerarArquivoInvalidoException());
        importadorArquivosItemReader.read();
        Mockito.verify(movedorArquivoRepositoryMock).moverParaInvalido(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT);
    }

    @Test
    public void aoChamarReadDadoQueSejaLancadoUmaRuntimeExceptionDeveriaRetonarNulo() {
        Mockito.when(leitorArquivoServiceMock.lerArquivoNaoImportado()).thenThrow(new RuntimeException(""));
        assertNull(importadorArquivosItemReader.read());
    }

    private ArquivoInvalidoException gerarArquivoInvalidoException() {
        ArquivoInvalidoException excecao = new ArquivoInvalidoException("");
        excecao.setArquivoPath(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT);
        return excecao;
    }
}