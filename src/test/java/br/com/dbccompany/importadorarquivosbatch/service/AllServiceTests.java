package br.com.dbccompany.importadorarquivosbatch.service;

import br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl.ConsolidadorPiorVendedorImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl.ConsolidadorQuantidadeClientesImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl.ConsolidadorQuantidadeVendedoresImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl.ConsolidadorVendaMaisCaraImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.impl.ProcessadorArquivoServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProcessadorArquivoServiceImplTest.class,
        ConsolidadorQuantidadeClientesImplTest.class,
        ConsolidadorQuantidadeVendedoresImplTest.class,
        ConsolidadorVendaMaisCaraImplTest.class,
        ConsolidadorPiorVendedorImplTest.class
})
public class AllServiceTests {
}
