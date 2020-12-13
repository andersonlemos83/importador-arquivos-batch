package br.com.dbccompany.importadorarquivosbatch.repository;

import br.com.dbccompany.importadorarquivosbatch.repository.impl.ExcluidorArquivoRepositoryFileTest;
import br.com.dbccompany.importadorarquivosbatch.repository.impl.GravadorArquivoRepositoryFileTest;
import br.com.dbccompany.importadorarquivosbatch.repository.impl.LeitorArquivoRepositoryFileTest;
import br.com.dbccompany.importadorarquivosbatch.repository.impl.MovedorArquivoRepositoryFileTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LeitorArquivoRepositoryFileTest.class,
        GravadorArquivoRepositoryFileTest.class,
        ExcluidorArquivoRepositoryFileTest.class,
        MovedorArquivoRepositoryFileTest.class
})
public class AllRepositoryTests {
}
