package br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl;

import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.RegistroParse;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComLayoutInvalidoException;
import br.com.dbccompany.importadorarquivosbatch.shared.excecao.RegistroComTipoDadoInvalidoException;
import lombok.extern.log4j.Log4j2;

import static br.com.dbccompany.importadorarquivosbatch.shared.util.ObjectMapperUtil.generateJson;

@Log4j2
public abstract class AbstractRegistroParse<T> implements RegistroParse<T> {

    protected abstract int obterQuantidadeCampos();

    protected abstract T gerarRegistro(String[] registro);

    protected abstract String obterNome();

    @Override
    public T parse(String[] registro) {
        log.debug("Entrando em AbstractRegistroParse: {}", generateJson(registro));
        if (registro.length != obterQuantidadeCampos()) {
            throw new RegistroComLayoutInvalidoException(registro, obterNome());
        }
        try {
            T tRegistro = gerarRegistro(registro);
            log.debug("Saindo de AbstractRegistroParse: {}", generateJson(tRegistro));
            return tRegistro;
        } catch (Exception excecao) {
            log.error("Ocorreu um erro durante a geração do registro {}: {}", obterNome(), generateJson(registro), excecao);
            throw new RegistroComTipoDadoInvalidoException(registro, obterNome());
        }
    }
}