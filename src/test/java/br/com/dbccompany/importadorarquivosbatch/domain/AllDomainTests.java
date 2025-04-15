package br.com.dbccompany.importadorarquivosbatch.domain;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.ItemTest;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.RegistroTest;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.VendaTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        RegistroTest.class,
        ItemTest.class,
        VendaTest.class
})
@SuppressWarnings("squid:S2187")
@SuiteDisplayName("Suite that gathers all unit tests of the Domain package")
public class AllDomainTests {

}