package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.builder.DadosLeituraBuilder;
import br.com.dbccompany.importadorarquivosbatch.repository.MovedorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.service.LeitorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.ArquivoInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ImportadorArquivosItemReaderTest {

    @InjectMocks
    private ImportadorArquivosItemReader importadorArquivosItemReader;

    @Mock
    private LeitorArquivoService leitorArquivoServiceMock;

    @Mock
    private MovedorArquivoRepository movedorArquivoRepositoryMock;

    @Test
    public void aoChamarReadDadoQueSejaComSucessoDeveriaRetonarOhDadosLeituraEsperado() {
        DadosLeitura dadosLeituraEsperado = DadosLeituraBuilder.umDadosLeitura().build();
        Mockito.when(leitorArquivoServiceMock.lerArquivoNaoImportado()).thenReturn(dadosLeituraEsperado);
        DadosLeitura dadosLeituraRetornado = importadorArquivosItemReader.read();
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