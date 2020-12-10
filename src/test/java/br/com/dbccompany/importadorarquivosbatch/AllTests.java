package br.com.dbccompany.importadorarquivosbatch;

import br.com.dbccompany.importadorarquivosbatch.cucumber.CucumberTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UnitTests.class,
        CucumberTest.class
})
public class AllTests {
}
