package br.com.dbccompany.importadorarquivosbatch;

import br.com.dbccompany.importadorarquivosbatch.batch.AllBatchTests;
import br.com.dbccompany.importadorarquivosbatch.domain.AllDomainTests;
import br.com.dbccompany.importadorarquivosbatch.repository.AllRepositoryTests;
import br.com.dbccompany.importadorarquivosbatch.service.AllServiceTests;
import br.com.dbccompany.importadorarquivosbatch.shared.AllSharedTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        AllBatchTests.class,
        AllDomainTests.class,
        AllRepositoryTests.class,
        AllServiceTests.class,
        AllSharedTests.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all Unit tests")
public class UnitTests {

}