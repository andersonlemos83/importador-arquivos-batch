package br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.repository.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.QuantidadeAtributosInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroParseException;

public abstract class AbstractRegistroParse<T> implements RegistroParse<T> {

    protected abstract int obterQuantidadeCampos();

    protected abstract T gerarRegistro(String[] registro);

    protected abstract String obterNome();

    @Override
    public T parse(String[] registro) {
        if (registro.length != obterQuantidadeCampos()) {
            throw new QuantidadeAtributosInvalidoException(registro, obterNome());
        }
        try {
            return gerarRegistro(registro);
        } catch (Exception excecao) {
            throw new RegistroParseException(registro, obterNome());
        }
    }
}