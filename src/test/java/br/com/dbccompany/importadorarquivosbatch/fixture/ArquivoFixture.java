package br.com.dbccompany.importadorarquivosbatch.fixture;

import br.com.dbccompany.importadorarquivosbatch.domain.Arquivo;

import static br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture.umaListaRegistrosArrayComTresRegistros;
import static br.com.dbccompany.importadorarquivosbatch.fixture.RegistroFixture.umaListaRegistrosArrayComUmRegistro;
import static br.com.dbccompany.importadorarquivosbatch.util.ConstanteTesteUtil.ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT;

public final class ArquivoFixture {

    private ArquivoFixture() {
    }

    public static Arquivo umArquivoQualquer() {
        return umArquivoComUmRegistro();
    }

    public static Arquivo umArquivoComUmRegistro() {
        return new Arquivo(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT, umaListaRegistrosArrayComUmRegistro());
    }

    public static Arquivo umArquivoComTresRegistros() {
        return new Arquivo(ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT, umaListaRegistrosArrayComTresRegistros());
    }
}
