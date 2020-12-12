package br.com.dbccompany.importadorarquivosbatch.service;

import br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl.ConsolidadorPiorVendedorImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl.ConsolidadorQuantidadeClientesImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl.ConsolidadorQuantidadeVendedoresImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.consolidador.impl.ConsolidadorVendaMaisCaraImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.impl.LeitorArquivoServiceImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.impl.ProcessadorArquivoServiceImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.parse.factory.impl.RegistroParseFactoryImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.parse.impl.ArquivoParseImplTest;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.ClienteParseTest;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.ItemParseTest;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.VendaParseTest;
import br.com.dbccompany.importadorarquivosbatch.service.parse.registro.impl.VendedorParseTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LeitorArquivoServiceImplTest.class,
        ArquivoParseImplTest.class,
        RegistroParseFactoryImplTest.class,
        ClienteParseTest.class,
        VendedorParseTest.class,
        VendaParseTest.class,
        ItemParseTest.class,
        ProcessadorArquivoServiceImplTest.class,
        ConsolidadorQuantidadeClientesImplTest.class,
        ConsolidadorQuantidadeVendedoresImplTest.class,
        ConsolidadorVendaMaisCaraImplTest.class,
        ConsolidadorPiorVendedorImplTest.class
})
public class AllServiceTests {
}