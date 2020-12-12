package br.com.dbccompany.importadorarquivosbatch.service.parse.registro;

public interface RegistroParse<T> {

    T parse(String[] registro);

}