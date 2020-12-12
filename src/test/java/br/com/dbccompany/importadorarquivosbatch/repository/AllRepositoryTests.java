package br.com.dbccompany.importadorarquivosbatch.repository;

import br.com.dbccompany.importadorarquivosbatch.repository.impl.ExcluidorArquivoRepositoryFileTest;
import br.com.dbccompany.importadorarquivosbatch.repository.impl.GravadorArquivoRepositoryFileTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GravadorArquivoRepositoryFileTest.class,
        ExcluidorArquivoRepositoryFileTest.class
})
public class AllRepositoryTests {
}
