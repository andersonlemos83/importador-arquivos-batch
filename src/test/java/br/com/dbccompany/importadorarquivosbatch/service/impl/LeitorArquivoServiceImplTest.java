package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.helper.util.ConstanteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5786") // Public required for JUnit test suite
@ExtendWith(SpringExtension.class)
public class LeitorArquivoServiceImplTest {

    @InjectMocks
    private LeitorArquivoServiceImpl leitorArquivoService;

    @Mock
    private LeitorArquivoRepository leitorArquivoRepositoryMock;

    @Mock
    private ArquivoParse arquivoParseMock;

    @Test
    public void aoLerArquivoNaoImportadoDeveriaRetonarOhDadosLeituraEsperado() {
        Path arquivoEntradaPathEsperado = ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
        Arquivo arquivo = new Arquivo(arquivoEntradaPathEsperado, emptyList());
        List<Registro> registrosEsperados = new ArrayList<>();

        Mockito.when(leitorArquivoRepositoryMock.lerArquivoNaoImportado()).thenReturn(arquivo);
        Mockito.when(arquivoParseMock.parse(arquivo)).thenReturn(registrosEsperados);

        DadosLeitura dadosLeitura = leitorArquivoService.lerArquivoNaoImportado();

        assertEquals(arquivoEntradaPathEsperado, dadosLeitura.getArquivoPath());
        assertEquals(registrosEsperados, dadosLeitura.getRegistros());
    }
}