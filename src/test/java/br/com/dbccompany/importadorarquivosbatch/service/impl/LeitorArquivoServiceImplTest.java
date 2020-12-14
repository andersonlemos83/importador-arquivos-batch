package br.com.dbccompany.importadorarquivosbatch.service.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;
import br.com.dbccompany.importadorarquivosbatch.domain.dados.DadosLeitura;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.repository.LeitorArquivoRepository;
import br.com.dbccompany.importadorarquivosbatch.service.LeitorArquivoService;
import br.com.dbccompany.importadorarquivosbatch.service.parse.ArquivoParse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LeitorArquivoServiceImplTest {

    private LeitorArquivoService leitorArquivoService;

    @Mock
    private LeitorArquivoRepository leitorArquivoRepositoryMock;

    @Mock
    private ArquivoParse arquivoParseMock;

    private Path arquivoEntradaPathEsperado;
    private Arquivo arquivo;
    private List<Registro> registrosEsperados;

    @Before
    public void inicializarContexto() {
        leitorArquivoService = new LeitorArquivoServiceImpl(leitorArquivoRepositoryMock, arquivoParseMock);

        arquivoEntradaPathEsperado = ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;
        arquivo = new Arquivo(arquivoEntradaPathEsperado, emptyList());
        registrosEsperados = new ArrayList<>();
    }

    @Test
    public void aoLerArquivoNaoImportadoDeveriaRetonarOhDadosLeituraEsperado() {
        Mockito.when(leitorArquivoRepositoryMock.lerArquivoNaoImportado()).thenReturn(arquivo);
        Mockito.when(arquivoParseMock.parse(arquivo)).thenReturn(registrosEsperados);

        final DadosLeitura dadosLeitura = leitorArquivoService.lerArquivoNaoImportado();

        assertEquals(arquivoEntradaPathEsperado, dadosLeitura.getArquivoPath());
        assertEquals(registrosEsperados, dadosLeitura.getRegistros());
    }
}