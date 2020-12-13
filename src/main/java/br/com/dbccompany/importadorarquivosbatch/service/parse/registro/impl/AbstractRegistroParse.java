package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComLayoutInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComTipoDadoInvalidoException;

public abstract class AbstractRegistroParse<T> implements RegistroParse<T> {

    protected abstract int obterQuantidadeCampos();

    protected abstract T gerarRegistro(String[] registro);

    protected abstract String obterNome();

    @Override
    public T parse(String[] registro) {
        if (registro.length != obterQuantidadeCampos()) {
            throw new RegistroComLayoutInvalidoException(registro, obterNome());
        }
        try {
            return gerarRegistro(registro);
        } catch (Exception excecao) {
            throw new RegistroComTipoDadoInvalidoException(registro, obterNome());
        }
    }
}