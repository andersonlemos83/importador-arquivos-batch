package br.com.dbccompany.importadorarquivosbatch.repository;

import br.com.dbccompany.importadorarquivosbatch.repository.impl.ExcluidorArquivoRepositoryFileTest;
import br.com.dbccompany.importadorarquivosbatch.repository.impl.GravadorArquivoRepositoryFileTest;
import br.com.dbccompany.importadorarquivosbatch.repository.impl.LeitorArquivoRepositoryFileTest;
import br.com.dbccompany.importadorarquivosbatch.repository.impl.MovedorArquivoRepositoryFileTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        LeitorArquivoRepositoryFileTest.class,
        GravadorArquivoRepositoryFileTest.class,
        ExcluidorArquivoRepositoryFileTest.class,
        MovedorArquivoRepositoryFileTest.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all unit tests of the Repository package")
public class AllRepositoryTests {

}