package br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.Registro;
import br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.InformacaoException;

import java.util.Arrays;

public abstract class AbstractRegistroParse implements RegistroParse {

    protected abstract int obterQuantidadeCampos();

    protected abstract Registro gerarRegistro(String[] registro);

    protected abstract String obterNome();

    @Override
    public Registro parse(String[] registro) {
        if (registro.length != obterQuantidadeCampos()) {
            throw new InformacaoException("teste");
        }
        try {
            return gerarRegistro(registro);
        } catch (Exception excecao) {
            throw new InformacaoException(Arrays.asList(registro).toString() + obterNome());
        }
    }
}