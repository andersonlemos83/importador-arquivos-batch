package br.com.dbccompany.importadorarquivosbatch.batch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ImportadorArquivosItemReaderTest.class,
        ImportadorArquivosItemProcessorTest.class,
        ImportadorArquivosItemWriterTest.class
})
public class AllBatchTests {
}