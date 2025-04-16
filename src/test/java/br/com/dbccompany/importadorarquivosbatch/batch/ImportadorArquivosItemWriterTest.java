package br.com.dbccompany.importadorarquivosbatch.batch;

import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosProcessamento;
import br.com.dbccompany.importadorarquivosbatch.repository.ExcluidorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.repository.GravadorArquivoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.batch.item.Chunk;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.dbccompany.importadorarquivosbatch.helper.fixture.DadosProcessamentoFixture.umDadosProcessamentoQualquer;
import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.same;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class ImportadorArquivosItemWriterTest {

    @InjectMocks
    private ImportadorArquivosItemWriter importadorArquivosItemWriter;

    @Mock
    private GravadorArquivoRepository gravadorArquivoRepositoryMock;

    @Mock
    private ExcluidorArquivoRepository excluidorArquivoRepositoryMock;

    @Test
    public void aoChamarWriteDadoQueSejaComSucessoDeveriaRealizarGravacaoDoConsolidadoIhRemocaoDoArquivoDeEntrada() {
        DadosProcessamento dadosProcessamento = umDadosProcessamentoQualquer();
        importadorArquivosItemWriter.write(new Chunk<>(singletonList(dadosProcessamento)));
        Mockito.verify(gravadorArquivoRepositoryMock).gravar(same(dadosProcessamento));
        Mockito.verify(excluidorArquivoRepositoryMock).excluir(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT);
    }
}