package br.com.dbccompany.importadorarquivosbatch.shared.excecao;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

@SuppressWarnings("java:S1165")
@Getter
@Setter
public class ArquivoInvalidoException extends RuntimeException {

    private transient Path arquivoPath;

    public ArquivoInvalidoException(String mensagem) {
        super(mensagem);
    }
}