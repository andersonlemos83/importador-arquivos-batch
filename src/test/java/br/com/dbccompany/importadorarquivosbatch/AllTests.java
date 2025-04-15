package br.com.dbccompany.importadorarquivosbatch;

import br.com.dbccompany.importadorarquivosbatch.cucumber.RunCucumberTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        UnitTests.class,
        RunCucumberTest.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all Unit and Acceptance tests")
public class AllTests {

}