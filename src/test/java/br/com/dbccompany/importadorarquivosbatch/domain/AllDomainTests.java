package br.com.dbccompany.importadorarquivosbatch.domain;

import br.com.dbccompany.importadorarquivosbatch.domain.registro.ItemTest;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.RegistroTest;
import br.com.dbccompany.importadorarquivosbatch.domain.registro.VendaTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegistroTest.class,
        ItemTest.class,
        VendaTest.class
})
public class AllDomainTests {
}
