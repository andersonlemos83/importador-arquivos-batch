package br.com.dbccompany.importadorarquivosbatch.repository;

import br.com.dbccompany.importadorarquivosbatch.repository.impl.ExcluidorArquivoRepositoryFileTest;
import br.com.dbccompany.importadorarquivosbatch.repository.impl.GravadorArquivoRepositoryFileTest;
import br.com.dbccompany.importadorarquivosbatch.repository.impl.LeitorArquivoRepositoryFileTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LeitorArquivoRepositoryFileTest.class,
        GravadorArquivoRepositoryFileTest.class,
        ExcluidorArquivoRepositoryFileTest.class
})
public class AllRepositoryTests {
}
