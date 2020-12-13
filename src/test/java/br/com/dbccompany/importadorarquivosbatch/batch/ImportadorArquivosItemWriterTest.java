package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.repository.ExcluidorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.dbccompany.importadorarquivosbatch.fixture.DadosProcessamentoFixture.umDadosProcessamentoQualquer;
import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.same;

@RunWith(MockitoJUnitRunner.class)
public class ImportadorArquivosItemWriterTest {

    private ImportadorArquivosItemWriter importadorArquivosItemWriter;

    @Mock
    private GravadorArquivoRepository gravadorArquivoRepositoryMock;

    @Mock
    private ExcluidorArquivoRepository excluidorArquivoRepositoryMock;

    @Before
    public void inicializarContexto() {
        importadorArquivosItemWriter = new ImportadorArquivosItemWriter(gravadorArquivoRepositoryMock, excluidorArquivoRepositoryMock);
    }

    @Test
    public void aoChamarWriteDadoQueSejaComSucessoDeveriaRealizarGravacaoDoConsolidadoIhRemocaoDoArquivoDeEntrada() {
        final DadosProcessamento dadosProcessamento = umDadosProcessamentoQualquer();
        importadorArquivosItemWriter.write(asList(dadosProcessamento));
        Mockito.verify(gravadorArquivoRepositoryMock).gravar(same(dadosProcessamento));
        Mockito.verify(excluidorArquivoRepositoryMock).excluir(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT);
    }
}