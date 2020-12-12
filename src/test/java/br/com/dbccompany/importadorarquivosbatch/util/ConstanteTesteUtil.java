package br.com.dbccompany.importadorarquivosbatch.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class ConstanteTesteUtil {

    private ConstanteTesteUtil() {
    }

    public static final String ARQUIVO_SUCESSO_DBC_DAT = "sucesso-dbc.dat";
    public static final String ARQUIVO_SUCESSO_DBC_TXT = "sucesso-dbc.txt";
    public static final String ARQUIVO_SUCESSO_DBC_DONE_DAT = "sucesso-dbc.done.dat";
    public static final String CONTEUDO_ARQUIVO_SUCESSO_DBC_DONE_DAT = "2ç2ç10çPaulo";
    public static final Path ARQUIVO_ENTRADA_PATH_SUCESSO_DBC_DAT = Paths.get("./data/in/sucesso-dbc.dat");

}