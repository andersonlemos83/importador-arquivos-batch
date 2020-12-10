package br.com.dbccompany.importadorarquivosbatch;

import br.com.dbccompany.importadorarquivosbatch.domain.AllDomainTests;
import br.com.dbccompany.importadorarquivosbatch.repository.AllRepositoryTests;
import br.com.dbccompany.importadorarquivosbatch.service.AllServiceTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllDomainTests.class,
        AllRepositoryTests.class,
        AllServiceTests.class
})
public class UnitTests {
}
