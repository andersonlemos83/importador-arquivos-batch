package br.com.dbccompany.importadorarquivosbatch.repository.parse.registro;

public interface RegistroParse<T> {

    T parse(String[] registro);

}